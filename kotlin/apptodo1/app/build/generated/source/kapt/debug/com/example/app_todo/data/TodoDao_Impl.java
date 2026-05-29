package com.example.app_todo.data;

import androidx.annotation.NonNull;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import java.lang.Class;
import java.lang.Long;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class TodoDao_Impl implements TodoDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<TodoEntity> __insertAdapterOfTodoEntity;

  private final EntityDeleteOrUpdateAdapter<TodoEntity> __deleteAdapterOfTodoEntity;

  private final EntityDeleteOrUpdateAdapter<TodoEntity> __updateAdapterOfTodoEntity;

  public TodoDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfTodoEntity = new EntityInsertAdapter<TodoEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `todos` (`id`,`title`,`done`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final TodoEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getTitle());
        }
        final int _tmp = entity.getDone() ? 1 : 0;
        statement.bindLong(3, _tmp);
      }
    };
    this.__deleteAdapterOfTodoEntity = new EntityDeleteOrUpdateAdapter<TodoEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `todos` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final TodoEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfTodoEntity = new EntityDeleteOrUpdateAdapter<TodoEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `todos` SET `id` = ?,`title` = ?,`done` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final TodoEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getTitle());
        }
        final int _tmp = entity.getDone() ? 1 : 0;
        statement.bindLong(3, _tmp);
        statement.bindLong(4, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final TodoEntity todo, final Continuation<? super Long> $completion) {
    if (todo == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      return __insertAdapterOfTodoEntity.insertAndReturnId(_connection, todo);
    }, $completion);
  }

  @Override
  public Object delete(final TodoEntity todo, final Continuation<? super Unit> $completion) {
    if (todo == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __deleteAdapterOfTodoEntity.handle(_connection, todo);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object update(final TodoEntity todo, final Continuation<? super Unit> $completion) {
    if (todo == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __updateAdapterOfTodoEntity.handle(_connection, todo);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Flow<List<TodoEntity>> observeAll() {
    final String _sql = "SELECT * FROM todos ORDER BY id DESC";
    return FlowUtil.createFlow(__db, false, new String[] {"todos"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
        final int _columnIndexOfDone = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "done");
        final List<TodoEntity> _result = new ArrayList<TodoEntity>();
        while (_stmt.step()) {
          final TodoEntity _item;
          final long _tmpId;
          _tmpId = _stmt.getLong(_columnIndexOfId);
          final String _tmpTitle;
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null;
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle);
          }
          final boolean _tmpDone;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfDone));
          _tmpDone = _tmp != 0;
          _item = new TodoEntity(_tmpId,_tmpTitle,_tmpDone);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
