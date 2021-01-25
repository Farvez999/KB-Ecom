package com.korearbazar.ecom.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.korearbazar.ecom.model.CartModel;

import java.util.ArrayList;
import java.util.List;

public class CartDB extends SQLiteOpenHelper {

    private String DB_PATH;
    public static SQLiteDatabase db;
    private static int DB_VERSION = 1;
    private static String DATABASE_NAME = "addToCartDB";
    private static String TABLE_NAME = "cartTable";
    public static String ID = "id";
    public static String TITLE = "title";
    public static String IMAGE = "image";
    public static String PRICE = "price";
    public static String QUANTITY = "quantity";
    public static String DP = "dp";
    public static String STOCK = "stock";
    public static String PRODUCTID = "productid";

    public CartDB(Context context){super(context,DATABASE_NAME,null,DB_VERSION);}

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    private static final String CREATE_TABLE=
            "CREATE TABLE " + TABLE_NAME + " ("
                    + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + TITLE + " TEXT, " // Need comma here
                    + IMAGE + " TEXT, " // Need comma here
                    + PRICE + " TEXT, "
                    + QUANTITY + " TEXT, "
                    + DP + " TEXT, "
                    + STOCK + " TEXT, "
                    + PRODUCTID + " TEXT "// Need not comma here, because this is last column of table
                    + ");";

//            "CREATE TABLE "+TABLE_NAME+"("
//            + ID+ " INTEGER PRIMARY KEY, "
//            +TITLE+" TEXT, "
//            +IMAGE+" TEXT, "
//            +PRICE+" TEXT, "
//            +QUANTITY+" TEXT, "
//            +DP+"TEXT, "
//            +STOCK+"TEXT, "
//            +PRODUCTID+"TEXT) ";
        //db.execSQL(CREATE_TABLE);


    //create a database table for my model class.this name is UserInfo ;
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    //Add user Information
    public long addInsert(CartModel prodModel){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues values= new ContentValues();
       // values.put( ID,prodModel.getId());
        values.put( TITLE,prodModel.getTitle());
        values.put( IMAGE,prodModel.getImage());
        values.put( PRICE,prodModel.getPrice());
        values.put( QUANTITY,prodModel.getQuantity());
        values.put( DP,prodModel.getDp());
        values.put( STOCK,prodModel.getStock());
        values.put(PRODUCTID,prodModel.getProduct_id());
        

        long insertData = sqLiteDatabase.insert( TABLE_NAME,null,values );
        sqLiteDatabase.close();

        return insertData;
    }

    public void openDataBase() throws SQLException {
        String myPath = DB_PATH + DATABASE_NAME;
        db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }


    public List<CartModel> getAllData(){
        List<CartModel> userDataList=new ArrayList();
        String sql="Select * from "+ TABLE_NAME;
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();

        Cursor cursor=sqLiteDatabase.rawQuery( sql,null );
        if(cursor.moveToFirst()){
            do{
                CartModel prodModel=new CartModel();
                prodModel.setId(Integer.parseInt( cursor.getString(0 ) ));
                prodModel.setTitle( cursor.getString( 1 ) );
                prodModel.setImage( cursor.getString( 2) );
                prodModel.setPrice( cursor.getString( 3 ) );
                prodModel.setQuantity( cursor.getString( 4) );
                prodModel.setDp( cursor.getString( 5) );
                prodModel.setStock( cursor.getString( 6) );
                prodModel.setProduct_id( cursor.getString( 7) );

                userDataList.add( prodModel );
            }while (cursor.moveToNext());
        }
        return  userDataList;
    }
    //update user Information in my database
    public int updateUserInfo(CartModel prodModel){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues values=new ContentValues(  );
        values.put( TITLE, prodModel.getTitle());
        values.put( IMAGE,prodModel.getImage());
        values.put( PRICE, prodModel.getPrice());
        values.put( QUANTITY,prodModel.getQuantity());
        values.put( DP,prodModel.getDp());
        values.put( STOCK,prodModel.getStock());
        values.put( PRODUCTID,prodModel.getProduct_id());
        return sqLiteDatabase.update( TABLE_NAME,values,ID +" = ?", new String[]{String.valueOf( prodModel.getId())});
    }
    //Delete user information from my SQLite Database
    public void deleteUser(CartModel info){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.delete( TABLE_NAME,ID + " = ?", new String[]{String.valueOf( info.getId())});
        sqLiteDatabase.close();

    }

//    public void deleteAll()
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL("delete from "+ TABLE_NAME);
//        db.close();
//    }

    //count how many user contacts avaiable in my SQLite Database
    public int getCountUser(){
        String countQuery="Select * From " +TABLE_NAME;
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery( countQuery,null );
        return cursor.getCount();
    }

    //retrive data from my SQLite Database
    public List<String> getData() {
        List<String> fileName = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor  cursor = db.rawQuery("select * from user",null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String id=cursor.getString( cursor.getColumnIndex( ID ) );
                String title = cursor.getString(cursor.getColumnIndex( TABLE_NAME ));
                String image = cursor.getString(cursor.getColumnIndex(IMAGE));
                String price = cursor.getString(cursor.getColumnIndex( PRICE ));
                String quantity = cursor.getString(cursor.getColumnIndex(QUANTITY));

                String dp = cursor.getString(cursor.getColumnIndex(DP));
                String stock = cursor.getString(cursor.getColumnIndex(STOCK));
                String product_id = cursor.getString(cursor.getColumnIndex(PRODUCTID));


                fileName.add("User id: " +id );
                fileName.add("User Title: "+title);
                fileName.add("User Image: "+image);
                fileName.add("User Price: "+price);
                fileName.add("User Quantity: "+quantity);
                fileName.add("User Dp: "+dp);
                fileName.add("User Stock: "+stock);
                fileName.add("User Product_id: "+product_id);
                cursor.moveToNext();
            }
            db.close();
        }
        return fileName;
    }


}
