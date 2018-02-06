package cs.apps.obg.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import cs.apps.obg.domain.FlagVO;

/**
 * Created by d1jun on 2018-01-24.
 */

public class DBHelper {
    private static final String DATABASE_NAME = "common_sense.db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase db;
    private DatabaseHelper mDBHelper;
    private Context mCtx;
    private static DBHelper instance = null;
    public DBHelper(Context context) {
        this.mCtx = context;
    }
    public static DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context.getApplicationContext());
        }
        return instance;
    }
    public DBHelper(){}
    private class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            //sqLiteDatabase.execSQL("create table oneline_memo(_id integer primary key AUTOINCREMENT, content text, date text, res_id integer)");
            /*sqLiteDatabase.execSQL("create table playlist(_id integer primary key AUTOINCREMENT, list_name text)");
            sqLiteDatabase.execSQL("create table list_music(music_id long, " +
                    "list_id integer, " +
                    "music_title text," +
                    "music_artist text, music_albumid long,music_album text, music_duration long, music_datapath text," +
                    "constraint list_id_fk foreign key(list_id) references playlist(_id))");*/
            sqLiteDatabase.execSQL("create table cs_flag(_id integer primary key AUTOINCREMENT, country text, continent integer, capital text, res_id integer, coninent_num integer)");
            //1. Europe
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Albania', 1, 'Tirana', 0x7f070067, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Andorra', 1, 'Andorra la Vella',0x7f070068, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Austria', 1, 'Vienna',0x7f070069, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Azerbaijan', 1, 'Baku',0x7f07006a, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Belarus', 1, 'Minsk',0x7f07006b, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Belgium',1, 'City of Brussels',0x7f07006c, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Bosnia and Herzegovina', 1, 'Sarajevo',0x7f07006d, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Bulgaria',1, 'Sofia',0x7f07006e, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Croatia', 1, 'Zagreb',0x7f07006f, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Cyprus', 1, 'Nicosia',0x7f070070, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Czech', 1, 'Prague',0x7f070071, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Denmark', 1, 'Copenhagen',0x7f070072, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Estonia', 1, 'Estonia',0x7f070073, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Finland', 1, 'Helsinki',0x7f070074, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('France', 1, 'Paris',0x7f070075, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Georgia', 1, 'Tbilisi',0x7f070076, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Germany', 1, 'Berlin',0x7f070077, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Greece', 1, 'Athens',0x7f070078, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Hungary', 1, 'Budapest',0x7f070079, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Iceland', 1, ' Reykjavik',0x7f07007a, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Ireland', 1,  'Dublin',0x7f07007b, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Italy', 1, 'Rome',0x7f07007c, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Kosovo', 1, 'Pristina',0x7f07007d, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Latvia', 1, 'Riga',0x7f07007e, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Liechtenstein',1, 'Vaduz',0x7f07007f, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Lithuania', 1 ,'Vilnius',0x7f070080, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Luxembourg', 1, 'Luxembourg City',0x7f070081, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Macedonia',1, 'Skopje',0x7f070082, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Malta', 1, 'Valletta',0x7f070083, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Moldova', 1, 'Chișinău',0x7f070084, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Monaco', 1, 'Monacom',0x7f070085, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Montenegro', 1, 'Podgorica',0x7f070086, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Netherlands', 1, 'Amsterdam',0x7f070087, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Norway', 1, 'Oslo',0x7f070088, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Poland', 1, ' Warsaw',0x7f070089, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Portugal', 1, 'Lisbon',0x7f07008a, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Romania', 1, 'Bucharest',0x7f07008b, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Russia', 1, 'Moscow',0x7f07008c, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('San Marino', 1, 'San Marino',0x7f07008d, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Serbia', 1, 'Belgrade',0x7f07008e, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Slovakia', 1, 'Bratislava',0x7f07008f, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Slovenia', 1, 'Ljubljana',0x7f070090, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Spain', 1, 'Madrid',0x7f070091, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Vatican City', 1, 'Vatican City',0x7f070092, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Sweden', 1, 'Stockholm',0x7f070093, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Switzerland', 1, 'Bern',0x7f070094, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Ukraine', 1, 'Kiev',0x7f070095, 1)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('United Kingdom', 1, 'London',0x7f070096, 1)");
            //2. Asia & Oceania
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Australia', 2, 'Canberra',0x7f070097,3)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Bangladesh', 2, 'Dhaka',0x7f070098,2)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Bhutan', 2, 'Thimphu',0x7f070099,2)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Brunei', 2, 'Bandar Seri Begawan',0x7f07009a,2)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Cambodia', 2, 'Phnom Penh',0x7f070089b,2)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('China', 2, 'Beijing',0x7f07009c,2)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('East Timor', 2, 'Dili',0x7f07009d,2)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Fiji', 2, 'Suva',0x7f07009e,3)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('India', 2, 'New Delhi',0x7f07009f,2)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Indonesia', 2, 'Jakarta',0x7f0700a0,2)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Japan', 2, 'Tokyo',0x7f0700a1,2)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Kazakhstan', 2, 'Astana',0x7f0700a2,2)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Kiribati', 2, 'South Tarawa',0x7f0700a3,3)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('South Korea', 2, 'Seoul',0x7f0700a4,2)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Kyrgyzstan', 2, 'Bishkek',0x7f0700a5,2)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Laos', 2, 'Vientiane',0x7f0700a6,2)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Malaysia', 2, 'Kuala Lumpur',0x7f0700a7,2)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Maldives', 2, 'Malé',0x7f0700a8,2)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Marshall Is.', 2, 'Majuro',0x7f0700a9,3)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Micronesia', 2, 'Palikir',0x7f0700aa,3)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Mongolia', 2, 'Ulaanbaatar',0x7f0700ab,2)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Myanmar', 2, 'Naypyidaw',0x7f0700ac,2)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Nauru', 2, 'Yaren',0x7f0700ad,3)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Nepal', 2, 'Kathmandu',0x7f0700ae,2)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('New Zealand', 2, 'Wellington',0x7f0700af,3)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Pakistan', 2, 'Islamabad',0x7f0700b0,2)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Palau', 2, 'Ngerulmud',0x7f0700b1,3)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Papua New Guinea', 2, 'Port Moresby',0x7f0700b2,3)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Philippines', 2,'Manila',0x7f0700b3,2)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Samoa', 2,'Apia',0x7f0700b4,3)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Singapore', 2,'Singapore',0x7f0700b5,2)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Solomon Is.', 2,'Honiara',0x7f0700b6,3)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Sri Lanka', 2,'Colombo',0x7f0700b7,2)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Taiwan', 2,'Taipei',0x7f0700b8,2)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Tajikistan', 2,'Dushanbe',0x7f0700b9,2)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Thailand', 2,'Bangkok',0x7f0700ba,2)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Tonga', 2,'Nukuʻalofa',0x7f0700bb,3)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Turkmenistan', 2,'Ashgabat',0x7f0700bc,2)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Tuvalu', 2,'Funafuti',0x7f0700bd,3)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Uzbekistan', 2,'Tashkent',0x7f0700be,2)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Vanuatu', 2,'Port Vila',0x7f0700bf,3)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Vietnam', 2,'Hanoi',0x7f0700c0,2)");
            //3. America
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Antigua and Barbuda', 3,'St. Johns',0x7f0700c2,4)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Argentina', 3,'Buenos Aires',0x7f0700c3,4)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Bahamas', 3,'Nassau',0x7f0700c4,4)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Barbados', 3,'Bridgetown',0x7f0700c5,4)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Belize', 3,'Belmopan',0x7f0700c6,4)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Bolivia', 3,'Sucre',0x7f0700c7,4)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Brazil', 3,'Brasília',0x7f0700c8,4)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Canada', 3,'Ottawa',0x7f0700c9,4)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Chile', 3,'Santiago',0x7f0700ca,4)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Colombia', 3,'Bogotá',0x7f0700cb,4)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Commonwealth of Dominica', 3,'Roseau',0x7f0700cc,4)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Costa Rica', 3,'San José',0x7f0700cd,4)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Cuba', 3,'Havana',0x7f0700ce,4)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Dominican Republic', 3,'Santo Domingo',0x7f0700cf,4)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Ecuador', 3,'Quito',0x7f0700d0,4)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('El SalvaDor', 3,'San Salvador',0x7f0700d1,4)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Greenland', 3,'Nuuk',0x7f0700d2,4)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Grenada', 3,'St. Georges',0x7f0700d3,4)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Guatemala', 3,'Guatemala City',0x7f0700d4,4)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Guyana', 3,'Georgetown',0x7f0700d5,4)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Haiti', 3,'Port-au-Prince',0x7f0700d6,4)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Honduras', 3,'Tegucigalpa',0x7f0700d7,4)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Jamaica', 3,'Kingston',0x7f0700d8,4)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Mexico', 3,'Mexico City',0x7f0700d9,4)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Nicaragua', 3,'Managua',0x7f0700da,4)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Panama', 3,'Panama City',0x7f0700db,4)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Paraguay', 3,'Asunción',0x7f0700dc,4)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Peru', 3,'Lima',0x7f0700dd,4)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Puerto Rico', 3,'San Juan',0x7f0700de,4)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Saint Kitts and Nevis', 3,'Basseterre',0x7f0700df,4)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Saint Lucia', 3,'Castries',0x7f0700e0,4)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Saint Vincent and the Grenadines', 3,'Kingstown',0x7f0700e1,4)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Suriname', 3,'Paramaribo',0x7f0700e2,4)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Trindad and Tobago', 3,'Port of Spain',0x7f0700e3,4)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Uruguay', 3,'Montevideo',0x7f0700e4,4)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('USA', 3,'Washington, D.C.',0x7f0700e5,4)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Venezuela', 3,'Caracas',0x7f0700e6,4)");
            //4. Africa & Middle East
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Afghanistan', 4,'Kabul',0x7f0700e8,6)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Algeria', 4,'Algiers',0x7f0700e9,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Angola', 4,'Luanda',0x7f0700ea,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Bahrain', 4,'Manama',0x7f0700eb,6)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Benin', 4,'Porto-Novo',0x7f0700ec,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Botswana', 4,'Gaborone',0x7f0700ed,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Burkina Faso', 4,'Ouagadougou',0x7f0700ee,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Burundi', 4,'Bujumbura',0x7f0700ef,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Cameroon', 4,'Yaoundé',0x7f0700f0,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Cape Verde', 4,'Praia',0x7f0700f1,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Cote d Ivoire', 4,'Yamoussoukro',0x7f0700f2,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Djibouti', 4,'Djibouti',0x7f0700f3,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Egypt', 4,'Cairo',0x7f0700f4,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Gabon', 4,'Libreville',0x7f0700f5,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Gambia', 4,'Banjul',0x7f0700f6,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Ghana', 4,'Accra',0x7f0700f7,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Guinea', 4,'Conakry',0x7f0700f8,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Guinea Bissau', 4,'Bissau',0x7f0700f9,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Iran', 4,'Tehran',0x7f0700fa,6)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Iraq', 4,'Baghdad',0x7f0700fb,6)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Israel', 4,'Jerusalem',0x7f0700fc,6)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Jordan', 4,'Amman',0x7f0700fd,6)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Kenya', 4,'Nairobi',0x7f0700fe,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Kuwait', 4,'Kuwait City',0x7f0700ff,6)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Lebanon', 4,'Beirut',0x7f070100,6)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Lesotho', 4,'Maseru',0x7f070101,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Liberia', 4,'Monrovia',0x7f070102,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Libya', 4,'Tripoli',0x7f0700103,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Madagascar', 4,'Antananarivo',0x7f0700104,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Malawi', 4,'Lilongwe',0x7f070105,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Mali', 4,'Bamako',0x7f070106,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Mauritania', 4,'Nouakchott',0x7f070107,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Mauritius', 4,'Port Louis',0x7f070108,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Morocco', 4,'Rabat',0x7f070109,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Mozambique', 4,'Maputo',0x7f07010a,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Namibia', 4,'Windhoek',0x7f07010b,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Niger', 4,' Niamey',0x7f07010c,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Nigeria', 4,'Abuja',0x7f07010d,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Qatar', 4,'Doha',0x7f07010e,6)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Republic of South Africa', 4,'Cape Town',0x7f07010f,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Rwanda', 4,' Kigali',0x7f070110,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Saudi Arabia', 4,'Riyadh',0x7f070111,6)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Senegal', 4,'Dakar',0x7f070112,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Somalia', 4,'Mogadishu',0x7f070113,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Republic of South Sudan', 4,'Juba',0x7f070114,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Sudan', 4,'Khartoum',0x7f070115,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Syria', 4,'Damascus',0x7f070116,6)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Togo', 4,'Lomé',0x7f070117,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Tunisia', 4,'Tunis',0x7f070118,5)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Turkey', 4,'Ankara',0x7f070119,6)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('United Arab Emirates', 4,'Abu Dhabi',0x7f07011a,6)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Yemen', 4,'Sana',0x7f07011b,6)");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num) values('Zambia', 4,'Lusaka',0x7f070109,5)");

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
    public DBHelper open() throws SQLException {
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        db = mDBHelper.getWritableDatabase();
        return this;
    }
    public ArrayList<FlagVO> selectData(int continentNum) {
        ArrayList<FlagVO> list = new ArrayList<>();
        FlagVO vo = null;
        String sql = "";
        sql = "select _id, country, continent, capital, res_id from cs_flag where continent = "+continentNum;
        Cursor cursor =  db.rawQuery(sql, null);
        for(int i = 0;i<cursor.getCount(); i++) {
            cursor.moveToNext();
            vo = new FlagVO();
            vo.set_id(cursor.getInt(0));
            vo.setCountry(cursor.getString(1));
            vo.setContinent(cursor.getString(2));
            vo.setCapital(cursor.getString(3));
            vo.setResId(cursor.getInt(4));
            list.add(vo);
        }
        cursor.close();
        return list;
    }
    public ArrayList<FlagVO> selectAllData(int continentNum) {
        ArrayList<FlagVO> list = new ArrayList<>();
        FlagVO vo = null;
        String sql = "select _id, country, continent, capital, res_id, coninent_num from cs_flag where coninent_num =" + continentNum;
        Cursor cursor = db.rawQuery(sql, null);
        for(int i = 0;i<cursor.getCount(); i++) {
            cursor.moveToNext();
            vo = new FlagVO();
            vo.set_id(cursor.getInt(0));
            vo.setCountry(cursor.getString(1));
            vo.setContinent(cursor.getString(2));
            vo.setCapital(cursor.getString(3));
            vo.setResId(cursor.getInt(4));
            vo.setContinentNum(cursor.getInt(5));
            list.add(vo);
        }
        return list;
    }
}
