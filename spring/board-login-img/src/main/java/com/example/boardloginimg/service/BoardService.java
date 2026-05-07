package com.example.boardloginimg.service;

import com.example.boardloginimg.domain.Board;
import com.example.boardloginimg.domain.BoardImage;
import com.example.boardloginimg.domain.User;
import com.example.boardloginimg.repository.BoardRepository;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Value("${app.upload-dir}")
    private String uploadDir;

    @PostConstruct //사전점검 한번실행 - 폴더없으면 폴더만들어라
    void ensureUploadDir() {
        File dir = new File(normalizeDir(uploadDir));
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IllegalStateException("업로드 폴더를 만들 수 없습니다: " + uploadDir);
        }
    }
    //mkdirs - 부모 폴더가 없으면 줄줄이 다만듬  c:/data/upload


    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public Board create(String title, String content, User author, List<MultipartFile> files) {
        Board board = new Board(title,content,author, LocalDateTime.now());
       boardRepository.save(board);
       storeImages(board, files);
       return boardRepository.findByIdWithAuthorAndImages(board.getId()).orElse(board);
    }



    private void storeImages(Board board, List<MultipartFile> files) {
        if(files == null || files.isEmpty()){
            return;
        }
        String dir = normalizeDir(uploadDir);
        for (MultipartFile file : files) {
            if (file == null || file.isEmpty()) {
                continue;
            }
            try {
                String originalName = file.getOriginalFilename();
                if (originalName == null || originalName.isBlank()) {
                    originalName = "file";
                }
                String savedName = UUID.randomUUID() + "_" + originalName;
                File saveFile = new File(dir + savedName);
                file.transferTo(saveFile);//임시 저장소에 저장-> 실제 하드디스크에 복사

                File thumbFile = new File(dir + "s_" + savedName);
                Thumbnails.of(saveFile).size(200, 200).toFile(thumbFile);
                //썸네일 생성 200*200

                BoardImage image = new BoardImage(originalName, savedName, dir, board);
                board.getImages().add(image);
            }
            catch (IOException e) {
                throw new RuntimeException("이미지 업로드 실패", e);
            }
        }
        boardRepository.save(board);
    }

    private String normalizeDir(String dir) {
        if (dir == null || dir.isBlank()) {
            return "";
        }
        return dir.endsWith("/") || dir.endsWith("\\") ? dir : dir + File.separator;
    }
//    window '/' 리눅스 "\\" 경로로 끝나면
    //  구분자가 없다면 File.separator 알아서 만들어줌 - 유효성검사후 알아서 붙여줌
    //예  디렉토리 c:/upload  업로드 이미지  test.jpg   둘이 합치면 c:/uploadtest.jpg
    // c:/upload/test.jpg


    public List<Board> findAll() {
        return boardRepository.findAllWithAuthor();
    }

    public Board findById(Long id) {
        return boardRepository.findByIdWithAuthorAndImages(id)
                .orElseThrow(() -> new IllegalArgumentException("글이 없습니다."));
    }

    @Transactional
    public void update(Long id, String title, String content, String currentUsername, List<MultipartFile> files) {
        Board board= boardRepository.findByIdWithAuthorAndImages(id)
                .orElseThrow(()->new IllegalArgumentException("글이 없습니다."));
        if(!board.getAuthor().getUsername().equals(currentUsername)){
            throw  new IllegalArgumentException("작성자만 수정 할 수 있습니다.");
        }
        board.update(title, content);
        storeImages(board,files);
    }

    public void delete(Long id, String currentUsername) {
       Board board = boardRepository.findByIdWithAuthorAndImages(id)
                .orElseThrow(()->new IllegalArgumentException("글이 없습니다."));
       if(!board.getAuthor().getUsername().equals(currentUsername)){
           throw new IllegalArgumentException("작성자만 삭제 할 수 있습니다.");
       }
       deleteImageFiles(board);
       boardRepository.delete(board);
    }
//img.getFilePath() -> db에 저장경로가 있으면 그거 사용 없으며 기본 업로드 경로 사용
    private void deleteImageFiles(Board board) {
        for(BoardImage img : board.getImages()){ //게시글에 달린 이미지 전부 순회
            String base= img.getFilePath() !=null ? img.getFilePath() : normalizeDir(uploadDir);
            File f = new File(base + img.getSavedName());
            File t = new File(base + img.getThumbnailSavedName());
            if(f.exists()){
                f.delete();
            }
            if(t.exists()){
                t.delete();
            }
        }


    }


    //findAll 전체를 조회하고
    //WithAuthor Board 엔티티 안에 WithAuthor필드가 있는지 찾는다.

    //Board조회 (author는 안가지옴)
    //트랜잭션끝(세션종료)
    //타임리프 p.author.name 접근
    //db 다시조회하려고시도
    //세션이 없고 터짐
}
