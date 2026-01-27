package com.coinny.storedcard.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coinny.storedcard.data.local.Converters;
import com.coinny.storedcard.data.local.entity.Card;
import com.coinny.storedcard.domain.model.CardStatus;
import com.coinny.storedcard.domain.model.CardType;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class CardDao_Impl implements CardDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Card> __insertionAdapterOfCard;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<Card> __deletionAdapterOfCard;

  private final EntityDeletionOrUpdateAdapter<Card> __updateAdapterOfCard;

  public CardDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCard = new EntityInsertionAdapter<Card>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `card` (`id`,`name`,`type`,`initial_value`,`current_value`,`expiry_date`,`status`,`created_at`,`updated_at`,`last_deduct_date`,`daily_rate`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Card entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        final String _tmp = __converters.fromCardType(entity.getType());
        if (_tmp == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, _tmp);
        }
        statement.bindDouble(4, entity.getInitialValue());
        statement.bindDouble(5, entity.getCurrentValue());
        if (entity.getExpiryDate() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getExpiryDate());
        }
        final String _tmp_1 = __converters.fromCardStatus(entity.getStatus());
        if (_tmp_1 == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, _tmp_1);
        }
        statement.bindLong(8, entity.getCreatedAt());
        statement.bindLong(9, entity.getUpdatedAt());
        if (entity.getLastDeductDate() == null) {
          statement.bindNull(10);
        } else {
          statement.bindLong(10, entity.getLastDeductDate());
        }
        if (entity.getDailyRate() == null) {
          statement.bindNull(11);
        } else {
          statement.bindDouble(11, entity.getDailyRate());
        }
      }
    };
    this.__deletionAdapterOfCard = new EntityDeletionOrUpdateAdapter<Card>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `card` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Card entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfCard = new EntityDeletionOrUpdateAdapter<Card>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `card` SET `id` = ?,`name` = ?,`type` = ?,`initial_value` = ?,`current_value` = ?,`expiry_date` = ?,`status` = ?,`created_at` = ?,`updated_at` = ?,`last_deduct_date` = ?,`daily_rate` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Card entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        final String _tmp = __converters.fromCardType(entity.getType());
        if (_tmp == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, _tmp);
        }
        statement.bindDouble(4, entity.getInitialValue());
        statement.bindDouble(5, entity.getCurrentValue());
        if (entity.getExpiryDate() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getExpiryDate());
        }
        final String _tmp_1 = __converters.fromCardStatus(entity.getStatus());
        if (_tmp_1 == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, _tmp_1);
        }
        statement.bindLong(8, entity.getCreatedAt());
        statement.bindLong(9, entity.getUpdatedAt());
        if (entity.getLastDeductDate() == null) {
          statement.bindNull(10);
        } else {
          statement.bindLong(10, entity.getLastDeductDate());
        }
        if (entity.getDailyRate() == null) {
          statement.bindNull(11);
        } else {
          statement.bindDouble(11, entity.getDailyRate());
        }
        statement.bindLong(12, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final Card card, final Continuation<? super Long> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfCard.insertAndReturnId(card);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object delete(final Card card, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfCard.handle(card);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object update(final Card card, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfCard.handle(card);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object getById(final long cardId, final Continuation<? super Card> arg1) {
    final String _sql = "SELECT * FROM card WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, cardId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Card>() {
      @Override
      @Nullable
      public Card call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfInitialValue = CursorUtil.getColumnIndexOrThrow(_cursor, "initial_value");
          final int _cursorIndexOfCurrentValue = CursorUtil.getColumnIndexOrThrow(_cursor, "current_value");
          final int _cursorIndexOfExpiryDate = CursorUtil.getColumnIndexOrThrow(_cursor, "expiry_date");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final int _cursorIndexOfLastDeductDate = CursorUtil.getColumnIndexOrThrow(_cursor, "last_deduct_date");
          final int _cursorIndexOfDailyRate = CursorUtil.getColumnIndexOrThrow(_cursor, "daily_rate");
          final Card _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final CardType _tmpType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfType);
            }
            _tmpType = __converters.toCardType(_tmp);
            final double _tmpInitialValue;
            _tmpInitialValue = _cursor.getDouble(_cursorIndexOfInitialValue);
            final double _tmpCurrentValue;
            _tmpCurrentValue = _cursor.getDouble(_cursorIndexOfCurrentValue);
            final Long _tmpExpiryDate;
            if (_cursor.isNull(_cursorIndexOfExpiryDate)) {
              _tmpExpiryDate = null;
            } else {
              _tmpExpiryDate = _cursor.getLong(_cursorIndexOfExpiryDate);
            }
            final CardStatus _tmpStatus;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfStatus);
            }
            _tmpStatus = __converters.toCardStatus(_tmp_1);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final Long _tmpLastDeductDate;
            if (_cursor.isNull(_cursorIndexOfLastDeductDate)) {
              _tmpLastDeductDate = null;
            } else {
              _tmpLastDeductDate = _cursor.getLong(_cursorIndexOfLastDeductDate);
            }
            final Double _tmpDailyRate;
            if (_cursor.isNull(_cursorIndexOfDailyRate)) {
              _tmpDailyRate = null;
            } else {
              _tmpDailyRate = _cursor.getDouble(_cursorIndexOfDailyRate);
            }
            _result = new Card(_tmpId,_tmpName,_tmpType,_tmpInitialValue,_tmpCurrentValue,_tmpExpiryDate,_tmpStatus,_tmpCreatedAt,_tmpUpdatedAt,_tmpLastDeductDate,_tmpDailyRate);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg1);
  }

  @Override
  public Flow<Card> getByIdFlow(final long cardId) {
    final String _sql = "SELECT * FROM card WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, cardId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"card"}, new Callable<Card>() {
      @Override
      @Nullable
      public Card call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfInitialValue = CursorUtil.getColumnIndexOrThrow(_cursor, "initial_value");
          final int _cursorIndexOfCurrentValue = CursorUtil.getColumnIndexOrThrow(_cursor, "current_value");
          final int _cursorIndexOfExpiryDate = CursorUtil.getColumnIndexOrThrow(_cursor, "expiry_date");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final int _cursorIndexOfLastDeductDate = CursorUtil.getColumnIndexOrThrow(_cursor, "last_deduct_date");
          final int _cursorIndexOfDailyRate = CursorUtil.getColumnIndexOrThrow(_cursor, "daily_rate");
          final Card _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final CardType _tmpType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfType);
            }
            _tmpType = __converters.toCardType(_tmp);
            final double _tmpInitialValue;
            _tmpInitialValue = _cursor.getDouble(_cursorIndexOfInitialValue);
            final double _tmpCurrentValue;
            _tmpCurrentValue = _cursor.getDouble(_cursorIndexOfCurrentValue);
            final Long _tmpExpiryDate;
            if (_cursor.isNull(_cursorIndexOfExpiryDate)) {
              _tmpExpiryDate = null;
            } else {
              _tmpExpiryDate = _cursor.getLong(_cursorIndexOfExpiryDate);
            }
            final CardStatus _tmpStatus;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfStatus);
            }
            _tmpStatus = __converters.toCardStatus(_tmp_1);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final Long _tmpLastDeductDate;
            if (_cursor.isNull(_cursorIndexOfLastDeductDate)) {
              _tmpLastDeductDate = null;
            } else {
              _tmpLastDeductDate = _cursor.getLong(_cursorIndexOfLastDeductDate);
            }
            final Double _tmpDailyRate;
            if (_cursor.isNull(_cursorIndexOfDailyRate)) {
              _tmpDailyRate = null;
            } else {
              _tmpDailyRate = _cursor.getDouble(_cursorIndexOfDailyRate);
            }
            _result = new Card(_tmpId,_tmpName,_tmpType,_tmpInitialValue,_tmpCurrentValue,_tmpExpiryDate,_tmpStatus,_tmpCreatedAt,_tmpUpdatedAt,_tmpLastDeductDate,_tmpDailyRate);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Card>> getAllCards() {
    final String _sql = "SELECT * FROM card ORDER BY created_at DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"card"}, new Callable<List<Card>>() {
      @Override
      @NonNull
      public List<Card> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfInitialValue = CursorUtil.getColumnIndexOrThrow(_cursor, "initial_value");
          final int _cursorIndexOfCurrentValue = CursorUtil.getColumnIndexOrThrow(_cursor, "current_value");
          final int _cursorIndexOfExpiryDate = CursorUtil.getColumnIndexOrThrow(_cursor, "expiry_date");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final int _cursorIndexOfLastDeductDate = CursorUtil.getColumnIndexOrThrow(_cursor, "last_deduct_date");
          final int _cursorIndexOfDailyRate = CursorUtil.getColumnIndexOrThrow(_cursor, "daily_rate");
          final List<Card> _result = new ArrayList<Card>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Card _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final CardType _tmpType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfType);
            }
            _tmpType = __converters.toCardType(_tmp);
            final double _tmpInitialValue;
            _tmpInitialValue = _cursor.getDouble(_cursorIndexOfInitialValue);
            final double _tmpCurrentValue;
            _tmpCurrentValue = _cursor.getDouble(_cursorIndexOfCurrentValue);
            final Long _tmpExpiryDate;
            if (_cursor.isNull(_cursorIndexOfExpiryDate)) {
              _tmpExpiryDate = null;
            } else {
              _tmpExpiryDate = _cursor.getLong(_cursorIndexOfExpiryDate);
            }
            final CardStatus _tmpStatus;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfStatus);
            }
            _tmpStatus = __converters.toCardStatus(_tmp_1);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final Long _tmpLastDeductDate;
            if (_cursor.isNull(_cursorIndexOfLastDeductDate)) {
              _tmpLastDeductDate = null;
            } else {
              _tmpLastDeductDate = _cursor.getLong(_cursorIndexOfLastDeductDate);
            }
            final Double _tmpDailyRate;
            if (_cursor.isNull(_cursorIndexOfDailyRate)) {
              _tmpDailyRate = null;
            } else {
              _tmpDailyRate = _cursor.getDouble(_cursorIndexOfDailyRate);
            }
            _item = new Card(_tmpId,_tmpName,_tmpType,_tmpInitialValue,_tmpCurrentValue,_tmpExpiryDate,_tmpStatus,_tmpCreatedAt,_tmpUpdatedAt,_tmpLastDeductDate,_tmpDailyRate);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Card>> getCardsByStatus(final CardStatus status) {
    final String _sql = "SELECT * FROM card WHERE status = ? ORDER BY created_at DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __converters.fromCardStatus(status);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"card"}, new Callable<List<Card>>() {
      @Override
      @NonNull
      public List<Card> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfInitialValue = CursorUtil.getColumnIndexOrThrow(_cursor, "initial_value");
          final int _cursorIndexOfCurrentValue = CursorUtil.getColumnIndexOrThrow(_cursor, "current_value");
          final int _cursorIndexOfExpiryDate = CursorUtil.getColumnIndexOrThrow(_cursor, "expiry_date");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final int _cursorIndexOfLastDeductDate = CursorUtil.getColumnIndexOrThrow(_cursor, "last_deduct_date");
          final int _cursorIndexOfDailyRate = CursorUtil.getColumnIndexOrThrow(_cursor, "daily_rate");
          final List<Card> _result = new ArrayList<Card>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Card _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final CardType _tmpType;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfType);
            }
            _tmpType = __converters.toCardType(_tmp_1);
            final double _tmpInitialValue;
            _tmpInitialValue = _cursor.getDouble(_cursorIndexOfInitialValue);
            final double _tmpCurrentValue;
            _tmpCurrentValue = _cursor.getDouble(_cursorIndexOfCurrentValue);
            final Long _tmpExpiryDate;
            if (_cursor.isNull(_cursorIndexOfExpiryDate)) {
              _tmpExpiryDate = null;
            } else {
              _tmpExpiryDate = _cursor.getLong(_cursorIndexOfExpiryDate);
            }
            final CardStatus _tmpStatus;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            }
            _tmpStatus = __converters.toCardStatus(_tmp_2);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final Long _tmpLastDeductDate;
            if (_cursor.isNull(_cursorIndexOfLastDeductDate)) {
              _tmpLastDeductDate = null;
            } else {
              _tmpLastDeductDate = _cursor.getLong(_cursorIndexOfLastDeductDate);
            }
            final Double _tmpDailyRate;
            if (_cursor.isNull(_cursorIndexOfDailyRate)) {
              _tmpDailyRate = null;
            } else {
              _tmpDailyRate = _cursor.getDouble(_cursorIndexOfDailyRate);
            }
            _item = new Card(_tmpId,_tmpName,_tmpType,_tmpInitialValue,_tmpCurrentValue,_tmpExpiryDate,_tmpStatus,_tmpCreatedAt,_tmpUpdatedAt,_tmpLastDeductDate,_tmpDailyRate);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Card>> getActiveCards() {
    final String _sql = "SELECT * FROM card WHERE status = 'ACTIVE' ORDER BY created_at DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"card"}, new Callable<List<Card>>() {
      @Override
      @NonNull
      public List<Card> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfInitialValue = CursorUtil.getColumnIndexOrThrow(_cursor, "initial_value");
          final int _cursorIndexOfCurrentValue = CursorUtil.getColumnIndexOrThrow(_cursor, "current_value");
          final int _cursorIndexOfExpiryDate = CursorUtil.getColumnIndexOrThrow(_cursor, "expiry_date");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final int _cursorIndexOfLastDeductDate = CursorUtil.getColumnIndexOrThrow(_cursor, "last_deduct_date");
          final int _cursorIndexOfDailyRate = CursorUtil.getColumnIndexOrThrow(_cursor, "daily_rate");
          final List<Card> _result = new ArrayList<Card>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Card _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final CardType _tmpType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfType);
            }
            _tmpType = __converters.toCardType(_tmp);
            final double _tmpInitialValue;
            _tmpInitialValue = _cursor.getDouble(_cursorIndexOfInitialValue);
            final double _tmpCurrentValue;
            _tmpCurrentValue = _cursor.getDouble(_cursorIndexOfCurrentValue);
            final Long _tmpExpiryDate;
            if (_cursor.isNull(_cursorIndexOfExpiryDate)) {
              _tmpExpiryDate = null;
            } else {
              _tmpExpiryDate = _cursor.getLong(_cursorIndexOfExpiryDate);
            }
            final CardStatus _tmpStatus;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfStatus);
            }
            _tmpStatus = __converters.toCardStatus(_tmp_1);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final Long _tmpLastDeductDate;
            if (_cursor.isNull(_cursorIndexOfLastDeductDate)) {
              _tmpLastDeductDate = null;
            } else {
              _tmpLastDeductDate = _cursor.getLong(_cursorIndexOfLastDeductDate);
            }
            final Double _tmpDailyRate;
            if (_cursor.isNull(_cursorIndexOfDailyRate)) {
              _tmpDailyRate = null;
            } else {
              _tmpDailyRate = _cursor.getDouble(_cursorIndexOfDailyRate);
            }
            _item = new Card(_tmpId,_tmpName,_tmpType,_tmpInitialValue,_tmpCurrentValue,_tmpExpiryDate,_tmpStatus,_tmpCreatedAt,_tmpUpdatedAt,_tmpLastDeductDate,_tmpDailyRate);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getExpiredCards(final long timestamp, final Continuation<? super List<Card>> arg1) {
    final String _sql = "SELECT * FROM card WHERE expiry_date IS NOT NULL AND expiry_date <= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, timestamp);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Card>>() {
      @Override
      @NonNull
      public List<Card> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfInitialValue = CursorUtil.getColumnIndexOrThrow(_cursor, "initial_value");
          final int _cursorIndexOfCurrentValue = CursorUtil.getColumnIndexOrThrow(_cursor, "current_value");
          final int _cursorIndexOfExpiryDate = CursorUtil.getColumnIndexOrThrow(_cursor, "expiry_date");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final int _cursorIndexOfLastDeductDate = CursorUtil.getColumnIndexOrThrow(_cursor, "last_deduct_date");
          final int _cursorIndexOfDailyRate = CursorUtil.getColumnIndexOrThrow(_cursor, "daily_rate");
          final List<Card> _result = new ArrayList<Card>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Card _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final CardType _tmpType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfType);
            }
            _tmpType = __converters.toCardType(_tmp);
            final double _tmpInitialValue;
            _tmpInitialValue = _cursor.getDouble(_cursorIndexOfInitialValue);
            final double _tmpCurrentValue;
            _tmpCurrentValue = _cursor.getDouble(_cursorIndexOfCurrentValue);
            final Long _tmpExpiryDate;
            if (_cursor.isNull(_cursorIndexOfExpiryDate)) {
              _tmpExpiryDate = null;
            } else {
              _tmpExpiryDate = _cursor.getLong(_cursorIndexOfExpiryDate);
            }
            final CardStatus _tmpStatus;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfStatus);
            }
            _tmpStatus = __converters.toCardStatus(_tmp_1);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final Long _tmpLastDeductDate;
            if (_cursor.isNull(_cursorIndexOfLastDeductDate)) {
              _tmpLastDeductDate = null;
            } else {
              _tmpLastDeductDate = _cursor.getLong(_cursorIndexOfLastDeductDate);
            }
            final Double _tmpDailyRate;
            if (_cursor.isNull(_cursorIndexOfDailyRate)) {
              _tmpDailyRate = null;
            } else {
              _tmpDailyRate = _cursor.getDouble(_cursorIndexOfDailyRate);
            }
            _item = new Card(_tmpId,_tmpName,_tmpType,_tmpInitialValue,_tmpCurrentValue,_tmpExpiryDate,_tmpStatus,_tmpCreatedAt,_tmpUpdatedAt,_tmpLastDeductDate,_tmpDailyRate);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg1);
  }

  @Override
  public Object getExpiringCards(final long currentTime, final long futureTime,
      final Continuation<? super List<Card>> arg2) {
    final String _sql = "SELECT * FROM card WHERE expiry_date IS NOT NULL AND expiry_date > ? AND expiry_date <= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, currentTime);
    _argIndex = 2;
    _statement.bindLong(_argIndex, futureTime);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Card>>() {
      @Override
      @NonNull
      public List<Card> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfInitialValue = CursorUtil.getColumnIndexOrThrow(_cursor, "initial_value");
          final int _cursorIndexOfCurrentValue = CursorUtil.getColumnIndexOrThrow(_cursor, "current_value");
          final int _cursorIndexOfExpiryDate = CursorUtil.getColumnIndexOrThrow(_cursor, "expiry_date");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final int _cursorIndexOfLastDeductDate = CursorUtil.getColumnIndexOrThrow(_cursor, "last_deduct_date");
          final int _cursorIndexOfDailyRate = CursorUtil.getColumnIndexOrThrow(_cursor, "daily_rate");
          final List<Card> _result = new ArrayList<Card>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Card _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final CardType _tmpType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfType);
            }
            _tmpType = __converters.toCardType(_tmp);
            final double _tmpInitialValue;
            _tmpInitialValue = _cursor.getDouble(_cursorIndexOfInitialValue);
            final double _tmpCurrentValue;
            _tmpCurrentValue = _cursor.getDouble(_cursorIndexOfCurrentValue);
            final Long _tmpExpiryDate;
            if (_cursor.isNull(_cursorIndexOfExpiryDate)) {
              _tmpExpiryDate = null;
            } else {
              _tmpExpiryDate = _cursor.getLong(_cursorIndexOfExpiryDate);
            }
            final CardStatus _tmpStatus;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfStatus);
            }
            _tmpStatus = __converters.toCardStatus(_tmp_1);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final Long _tmpLastDeductDate;
            if (_cursor.isNull(_cursorIndexOfLastDeductDate)) {
              _tmpLastDeductDate = null;
            } else {
              _tmpLastDeductDate = _cursor.getLong(_cursorIndexOfLastDeductDate);
            }
            final Double _tmpDailyRate;
            if (_cursor.isNull(_cursorIndexOfDailyRate)) {
              _tmpDailyRate = null;
            } else {
              _tmpDailyRate = _cursor.getDouble(_cursorIndexOfDailyRate);
            }
            _item = new Card(_tmpId,_tmpName,_tmpType,_tmpInitialValue,_tmpCurrentValue,_tmpExpiryDate,_tmpStatus,_tmpCreatedAt,_tmpUpdatedAt,_tmpLastDeductDate,_tmpDailyRate);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg2);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
