package com.himanshu.foodrunnerapp.datbase;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class RestaurantDao_Impl implements RestaurantDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<RestaurantEntity> __insertionAdapterOfRestaurantEntity;

  private final EntityDeletionOrUpdateAdapter<RestaurantEntity> __deletionAdapterOfRestaurantEntity;

  public RestaurantDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRestaurantEntity = new EntityInsertionAdapter<RestaurantEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `favRes` (`res_id`,`res_name`,`res_rating`,`res_cost`,`res_image`) VALUES (?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, RestaurantEntity value) {
        if (value.getResId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getResId());
        }
        if (value.getResName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getResName());
        }
        if (value.getResRating() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getResRating());
        }
        if (value.getResCostforOne() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getResCostforOne());
        }
        if (value.getResImage() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getResImage());
        }
      }
    };
    this.__deletionAdapterOfRestaurantEntity = new EntityDeletionOrUpdateAdapter<RestaurantEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `favRes` WHERE `res_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, RestaurantEntity value) {
        if (value.getResId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getResId());
        }
      }
    };
  }

  @Override
  public void addToFav(final RestaurantEntity restaurantEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfRestaurantEntity.insert(restaurantEntity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteFromFav(final RestaurantEntity restaurantEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfRestaurantEntity.handle(restaurantEntity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<RestaurantEntity> getAllRes() {
    final String _sql = "SELECT * FROM favRes";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfResId = CursorUtil.getColumnIndexOrThrow(_cursor, "res_id");
      final int _cursorIndexOfResName = CursorUtil.getColumnIndexOrThrow(_cursor, "res_name");
      final int _cursorIndexOfResRating = CursorUtil.getColumnIndexOrThrow(_cursor, "res_rating");
      final int _cursorIndexOfResCostforOne = CursorUtil.getColumnIndexOrThrow(_cursor, "res_cost");
      final int _cursorIndexOfResImage = CursorUtil.getColumnIndexOrThrow(_cursor, "res_image");
      final List<RestaurantEntity> _result = new ArrayList<RestaurantEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final RestaurantEntity _item;
        final String _tmpResId;
        if (_cursor.isNull(_cursorIndexOfResId)) {
          _tmpResId = null;
        } else {
          _tmpResId = _cursor.getString(_cursorIndexOfResId);
        }
        final String _tmpResName;
        if (_cursor.isNull(_cursorIndexOfResName)) {
          _tmpResName = null;
        } else {
          _tmpResName = _cursor.getString(_cursorIndexOfResName);
        }
        final String _tmpResRating;
        if (_cursor.isNull(_cursorIndexOfResRating)) {
          _tmpResRating = null;
        } else {
          _tmpResRating = _cursor.getString(_cursorIndexOfResRating);
        }
        final String _tmpResCostforOne;
        if (_cursor.isNull(_cursorIndexOfResCostforOne)) {
          _tmpResCostforOne = null;
        } else {
          _tmpResCostforOne = _cursor.getString(_cursorIndexOfResCostforOne);
        }
        final String _tmpResImage;
        if (_cursor.isNull(_cursorIndexOfResImage)) {
          _tmpResImage = null;
        } else {
          _tmpResImage = _cursor.getString(_cursorIndexOfResImage);
        }
        _item = new RestaurantEntity(_tmpResId,_tmpResName,_tmpResRating,_tmpResCostforOne,_tmpResImage);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public RestaurantEntity getFavResByID(final String resId) {
    final String _sql = "SELECT * FROM favRes WHERE res_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (resId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, resId);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfResId = CursorUtil.getColumnIndexOrThrow(_cursor, "res_id");
      final int _cursorIndexOfResName = CursorUtil.getColumnIndexOrThrow(_cursor, "res_name");
      final int _cursorIndexOfResRating = CursorUtil.getColumnIndexOrThrow(_cursor, "res_rating");
      final int _cursorIndexOfResCostforOne = CursorUtil.getColumnIndexOrThrow(_cursor, "res_cost");
      final int _cursorIndexOfResImage = CursorUtil.getColumnIndexOrThrow(_cursor, "res_image");
      final RestaurantEntity _result;
      if(_cursor.moveToFirst()) {
        final String _tmpResId;
        if (_cursor.isNull(_cursorIndexOfResId)) {
          _tmpResId = null;
        } else {
          _tmpResId = _cursor.getString(_cursorIndexOfResId);
        }
        final String _tmpResName;
        if (_cursor.isNull(_cursorIndexOfResName)) {
          _tmpResName = null;
        } else {
          _tmpResName = _cursor.getString(_cursorIndexOfResName);
        }
        final String _tmpResRating;
        if (_cursor.isNull(_cursorIndexOfResRating)) {
          _tmpResRating = null;
        } else {
          _tmpResRating = _cursor.getString(_cursorIndexOfResRating);
        }
        final String _tmpResCostforOne;
        if (_cursor.isNull(_cursorIndexOfResCostforOne)) {
          _tmpResCostforOne = null;
        } else {
          _tmpResCostforOne = _cursor.getString(_cursorIndexOfResCostforOne);
        }
        final String _tmpResImage;
        if (_cursor.isNull(_cursorIndexOfResImage)) {
          _tmpResImage = null;
        } else {
          _tmpResImage = _cursor.getString(_cursorIndexOfResImage);
        }
        _result = new RestaurantEntity(_tmpResId,_tmpResName,_tmpResRating,_tmpResCostforOne,_tmpResImage);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
