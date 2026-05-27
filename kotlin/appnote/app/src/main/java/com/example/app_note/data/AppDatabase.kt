package com.example.app_note.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//AppDataBase = SqLite파일 note.db + Entity + Dao를 연결하는 허브
@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao //@Database + Dao 연결

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase { //싱글톤
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "notes.db",
                ).build().also { instance = it } //만들어진 DB를 instance 저장 -> 다음부터 재사용
            }
        }
    }
}
