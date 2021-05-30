package com.example.aplicativomobileadminiscar.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class DaoLogin extends SQLiteOpenHelper {
    private final String TABELA_LOGIN = "TB_LOGIN";

    public DaoLogin(@Nullable Context context ){

        super(context, "DB_ADMINISCAR", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String comandoLogin = "CREATE TABLE "+ TABELA_LOGIN +" (" +
                "ID INTEGER PRIMARY KEY," +
                "NOME VARCHAR(100)," +
                "USUARIO VARCHAR(50)," +
                "SENHA VARCHAR(15))";
        sqLiteDatabase.execSQL(comandoLogin);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // validação de login
    public ArrayList<DtoLogin> consultarPorUsuarioESenha(String usuario, String senha ){
        String comandoCli  = "SELECT * FROM " + TABELA_LOGIN + " WHERE USUARIO= '" + usuario + "'AND SENHA= '" + senha +"'";
        Cursor cursor = getReadableDatabase().rawQuery(comandoCli,null);
        ArrayList<DtoLogin> listaLogin = new ArrayList<>();

        while (cursor.moveToNext()){
            DtoLogin dtoLogin = new DtoLogin();
            dtoLogin.setId(cursor.getInt(0));
            dtoLogin.setNome(cursor.getString(1));
            dtoLogin.setUsuario(cursor.getString(2));
            dtoLogin.setSenha(cursor.getString(3));

            listaLogin.add(dtoLogin);
        }

        return listaLogin;
    }
}