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
            sqLiteDatabase.execSQL("create table cs_flag(_id integer primary key AUTOINCREMENT, country text, continent integer, capital text, res_id integer, coninent_num integer, country_kr text, capital_kr text)");
            //1. Europe
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Albania', 1, 'Tirana', 0x7f070067, 1,'알바이나', '티라나')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Andorra', 1, 'Andorra la Vella',0x7f070068, 1,'안도라','안도라라베야')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Austria', 1, 'Vienna',0x7f070069, 1,'오스트리아','빈')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Azerbaijan', 1, 'Baku',0x7f07006a, 1,'아제르바이잔','바쿠')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Belarus', 1, 'Minsk',0x7f07006b, 1,'벨라루스','민스크')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Belgium',1, 'City of Brussels',0x7f07006c, 1,'벨기에','브뤼셀')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Bosnia and Herzegovina', 1, 'Sarajevo',0x7f07006d, 1,'보스니아헤르체고비나','사라예보')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Bulgaria',1, 'Sofia',0x7f07006e, 1,'불가리아','소피아')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Croatia', 1, 'Zagreb',0x7f07006f, 1,'크로아티아','자그레브')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Cyprus', 1, 'Nicosia',0x7f070070, 1,'키프로스','니코시아')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Czech', 1, 'Prague',0x7f070071, 1,'체코','프라하')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Denmark', 1, 'Copenhagen',0x7f070072, 1,'덴마크','코펜하겐')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Estonia', 1, 'Estonia',0x7f070073, 1,'에스토니아','탈린')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Finland', 1, 'Helsinki',0x7f070074, 1,'핀란드','헬싱키')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('France', 1, 'Paris',0x7f070075, 1,'프랑스','파리')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Georgia', 1, 'Tbilisi',0x7f070076, 1,'조지아','트빌리시')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Germany', 1, 'Berlin',0x7f070077, 1,'독일','베를린')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Greece', 1, 'Athens',0x7f070078, 1,'그리스','아테네')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Hungary', 1, 'Budapest',0x7f070079, 1,'헝가리','부다페스트')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Iceland', 1, ' Reykjavik',0x7f07007a, 1,'아이슬란드','레이캬비크')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Ireland', 1,  'Dublin',0x7f07007b, 1,'아일랜드','더블린')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Italy', 1, 'Rome',0x7f07007c, 1,'이탈리아','로마')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Kosovo', 1, 'Pristina',0x7f07007d, 1,'코소보','프리슈티나')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Latvia', 1, 'Riga',0x7f07007e, 1,'라트비아','리가')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Liechtenstein',1, 'Vaduz',0x7f07007f, 1,'리히텐슈타인','파두츠')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Lithuania', 1 ,'Vilnius',0x7f070080, 1,'리투아니아','빌뉴스')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Luxembourg', 1, 'Luxembourg City',0x7f070081, 1,'룩셈부르크','룩셈부르크')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Macedonia',1, 'Skopje',0x7f070082, 1,'마케도니아','스코페')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Malta', 1, 'Valletta',0x7f070083, 1,'몰타','발레타')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Moldova', 1, 'Chișinău',0x7f070084, 1,'몰도바','키시너우')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Monaco', 1, 'Monaco',0x7f070085, 1,'모나코','모나코')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Montenegro', 1, 'Podgorica',0x7f070086, 1,'몬테네그로','포드고리차'");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Netherlands', 1, 'Amsterdam',0x7f070087, 1,'네덜란드','암스테르담')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Norway', 1, 'Oslo',0x7f070088, 1,'노르웨이','오슬로')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Poland', 1, ' Warsaw',0x7f070089, 1,'폴란드','바르샤바')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Portugal', 1, 'Lisbon',0x7f07008a, 1,'포르투갈','리스본')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Romania', 1, 'Bucharest',0x7f07008b, 1,'루마니아','부쿠레슈티')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Russia', 1, 'Moscow',0x7f07008c, 1,'러시아','모스크바')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('San Marino', 1, 'San Marino',0x7f07008d, 1,'산마리노','산마리노')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Serbia', 1, 'Belgrade',0x7f07008e, 1,'세르비아','베오그라드')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Slovakia', 1, 'Bratislava',0x7f07008f, 1,'슬로바이카','브라티슬라바')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Slovenia', 1, 'Ljubljana',0x7f070090, 1,'슬로베니아','류블랴나')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Spain', 1, 'Madrid',0x7f070091, 1,'스페인','마드리드')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Vatican City', 1, 'Vatican City',0x7f070092, 1,'바티칸시국','바티칸시국')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Sweden', 1, 'Stockholm',0x7f070093, 1,'스웨덴','스톡홀롬')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Switzerland', 1, 'Bern',0x7f070094, 1,'스위스','베른')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Ukraine', 1, 'Kiev',0x7f070095, 1,'우크라이나','키예브')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('United Kingdom', 1, 'London',0x7f070096, 1,'영국','런던')");
            //2. Asia & Oceania
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Australia', 2, 'Canberra',0x7f070097,3,'오스트레일리아','캔버라')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Bangladesh', 2, 'Dhaka',0x7f070098,2,'방글라데시','바카')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Bhutan', 2, 'Thimphu',0x7f070099,2,'부탄','팀부')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Brunei', 2, 'Bandar Seri Begawan',0x7f07009a,2,'브루나이','반다르스리브가완')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Cambodia', 2, 'Phnom Penh',0x7f07009b,2,'캄보디아','프놈펜')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('China', 2, 'Beijing',0x7f07009c,2,'중국','베이징')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('East Timor', 2, 'Dili',0x7f07009d,2,'동티모르','딜리')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Fiji', 2, 'Suva',0x7f07009e,3,'피지','수바')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('India', 2, 'New Delhi',0x7f07009f,2,'인도','뉴델리')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Indonesia', 2, 'Jakarta',0x7f0700a0,2,'인도네시아','자카르타')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Japan', 2, 'Tokyo',0x7f0700a1,2,'일본','도쿄')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Kazakhstan', 2, 'Astana',0x7f0700a2,2,'카자흐스탄','아스타나')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Kiribati', 2, 'South Tarawa',0x7f0700a3,3,'키리바시','사우스타라와')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('South Korea', 2, 'Seoul',0x7f0700a4,2,'대한민국','서울')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Kyrgyzstan', 2, 'Bishkek',0x7f0700a5,2,'키르기스스탄','비슈케크')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Laos', 2, 'Vientiane',0x7f0700a6,2,'라오스','비엔티안')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Malaysia', 2, 'Kuala Lumpur',0x7f0700a7,2,'말레이시아','쿠알라룸푸르')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Maldives', 2, 'Malé',0x7f0700a8,2,'몰디브','말레')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Marshall Is.', 2, 'Majuro',0x7f0700a9,3,'마셜 제도','마주로')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Micronesia', 2, 'Palikir',0x7f0700aa,3,'미크로네시아 연방','팔리키르')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Mongolia', 2, 'Ulaanbaatar',0x7f0700ab,2,'몽골','올란바토르')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Myanmar', 2, 'Naypyidaw',0x7f0700ac,2,'미얀마','네피도')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Nauru', 2, 'Yaren',0x7f0700ad,3,'나우루','야렌')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Nepal', 2, 'Kathmandu',0x7f0700ae,2,'네팔','카트만두')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('New Zealand', 2, 'Wellington',0x7f0700af,3,'뉴질랜드','웰링턴')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Pakistan', 2, 'Islamabad',0x7f0700b0,2,'파키스탄','이슬라마바드')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Palau', 2, 'Ngerulmud',0x7f0700b1,3,'팔라우','응게룰무드')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Papua New Guinea', 2, 'Port Moresby',0x7f0700b2,3,'파푸아뉴기니','포트모르즈비')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Philippines', 2,'Manila',0x7f0700b3,2,'필리핀','마닐라')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Samoa', 2,'Apia',0x7f0700b4,3,'사모아','아피아')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Singapore', 2,'Singapore',0x7f0700b5,2,'싱가포르','싱가포르')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Solomon Is.', 2,'Honiara',0x7f0700b6,3,'솔로몬 제도','호니아라')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Sri Lanka', 2,'Colombo',0x7f0700b7,2,'스리랑카','콜롬보')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Taiwan', 2,'Taipei',0x7f0700b8,2,'타이완','타이베이')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Tajikistan', 2,'Dushanbe',0x7f0700b9,2,'타지키스탄','두샨베')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Thailand', 2,'Bangkok',0x7f0700ba,2,'태국','방콕')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Tonga', 2,'Nukuʻalofa',0x7f0700bb,3,'통가','누쿠알로파')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Turkmenistan', 2,'Ashgabat',0x7f0700bc,2,'투르크메니스탄','아시가바트')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Tuvalu', 2,'Funafuti',0x7f0700bd,3,'투발루','푸나푸티')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Uzbekistan', 2,'Tashkent',0x7f0700be,2,'우즈베키스탄','타슈켄트')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Vanuatu', 2,'Port Vila',0x7f0700bf,3,'바누아투','포트빌라')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Vietnam', 2,'Hanoi',0x7f0700c0,2,'베트남','하노이')");
            //3. America
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Antigua and Barbuda', 3,'St. Johns',0x7f0700c2,4,'앤티가 바부다','세인트존스')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Argentina', 3,'Buenos Aires',0x7f0700c3,4,'아르헨티나','부에노아이레스')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Bahamas', 3,'Nassau',0x7f0700c4,4,'바하마','나소')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Barbados', 3,'Bridgetown',0x7f0700c5,4,'바베이도스','브리지타운')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Belize', 3,'Belmopan',0x7f0700c6,4,'벨리즈','벨모판')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Bolivia', 3,'Sucre',0x7f0700c7,4,'볼리비아','수크레')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Brazil', 3,'Brasília',0x7f0700c8,4,'브라질','브라질리아')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Canada', 3,'Ottawa',0x7f0700c9,4,'캐나다','오타와')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Chile', 3,'Santiago',0x7f0700ca,4,'칠레','산티아고')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Colombia', 3,'Bogotá',0x7f0700cb,4,'콜롬비아','보고타')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Commonwealth of Dominica', 3,'Roseau',0x7f0700cc,4,'도미니카 연방','로조')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Costa Rica', 3,'San José',0x7f0700cd,4,'코스타리카','산호세')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Cuba', 3,'Havana',0x7f0700ce,4,'쿠바','아바나')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Dominican Republic', 3,'Santo Domingo',0x7f0700cf,4,'도미니카 공화국','산토도밍고')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Ecuador', 3,'Quito',0x7f0700d0,4,'에콰도르','키토')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('El SalvaDor', 3,'San Salvador',0x7f0700d1,4,'엘살바도르','산살바도르')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Greenland', 3,'Nuuk',0x7f0700d2,4,'그린란드','누크')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Grenada', 3,'St. Georges',0x7f0700d3,4,'그레나다','세인트조지스')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Guatemala', 3,'Guatemala City',0x7f0700d4,4,'과테말라','과테말라')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Guyana', 3,'Georgetown',0x7f0700d5,4,'가이아나','조지타운')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Haiti', 3,'Port-au-Prince',0x7f0700d6,4,'아이티','포르토프랭스')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Honduras', 3,'Tegucigalpa',0x7f0700d7,4,'온두라스','테구시갈파')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Jamaica', 3,'Kingston',0x7f0700d8,4,'자메이카','킹스턴')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Mexico', 3,'Mexico City',0x7f0700d9,4,'멕시코','멕시코시티')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Nicaragua', 3,'Managua',0x7f0700da,4,'니카라과','마나과')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Panama', 3,'Panama City',0x7f0700db,4,'파나마','파나마')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Paraguay', 3,'Asunción',0x7f0700dc,4,'파라과이','아순시온')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Peru', 3,'Lima',0x7f0700dd,4,'페루','리마')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Puerto Rico', 3,'San Juan',0x7f0700de,4,'푸에르토리코','산후안')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Saint Kitts and Nevis', 3,'Basseterre',0x7f0700df,4,'세인트키츠 네비스','바스테르')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Saint Lucia', 3,'Castries',0x7f0700e0,4,'세인트루시아','캐스트리스')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Saint Vincent and the Grenadines', 3,'Kingstown',0x7f0700e1,4,'세인트빈센트 그레나딘','킹스타운')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Suriname', 3,'Paramaribo',0x7f0700e2,4,'수리남','파라마리보')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Trindad and Tobago', 3,'Port of Spain',0x7f0700e3,4,'트리니다드 토바고','포트오브스페인')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Uruguay', 3,'Montevideo',0x7f0700e4,4,'우루과이','몬테비데오')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('USA', 3,'Washington D.C.',0x7f0700e5,4,'미국','워싱턴 D.C')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Venezuela', 3,'Caracas',0x7f0700e6,4,'베네수엘라','카라카스')");
            //4. Africa & Middle East
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Afghanistan', 4,'Kabul',0x7f0700e8,6,'아프가니스탄','카불')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Algeria', 4,'Algiers',0x7f0700e9,5,'알제리','알제')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Angola', 4,'Luanda',0x7f0700ea,5,'앙골라','루안다')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Bahrain', 4,'Manama',0x7f0700eb,6,'바레인','마나마')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Benin', 4,'Porto-Novo',0x7f0700ec,5,'베냉','포르토노보')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Botswana', 4,'Gaborone',0x7f0700ed,5,'보츠와나','가보르네')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Burkina Faso', 4,'Ouagadougou',0x7f0700ee,5,'부르키나파소','와가두구')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Burundi', 4,'Bujumbura',0x7f0700ef,5,'부룬디','부줌부라')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Cameroon', 4,'Yaoundé',0x7f0700f0,5,'카메룬','야운데')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Cape Verde', 4,'Praia',0x7f0700f1,5,'카보베르데','프라이아')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Cote d Ivoire', 4,'Yamoussoukro',0x7f0700f2,5,'코트디부아르','야무수크로')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Djibouti', 4,'Djibouti',0x7f0700f3,5,'지부티','지부티')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Egypt', 4,'Cairo',0x7f0700f4,5,'이집트','카이로')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Gabon', 4,'Libreville',0x7f0700f5,5,'가봉','리브르빌')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Gambia', 4,'Banjul',0x7f0700f6,5,'감비아','반줄')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Ghana', 4,'Accra',0x7f0700f7,5,'가나','아크라')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Guinea', 4,'Conakry',0x7f0700f8,5,'기니','코나크리')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Guinea Bissau', 4,'Bissau',0x7f0700f9,5,'기니비사우','비사우')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Iran', 4,'Tehran',0x7f0700fa,6,'이란','테헤란')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Iraq', 4,'Baghdad',0x7f0700fb,6,'이라크','바그다드')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Israel', 4,'Jerusalem',0x7f0700fc,6,'이스라엘','예루살렘')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Jordan', 4,'Amman',0x7f0700fd,6,'요르단','암만')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Kenya', 4,'Nairobi',0x7f0700fe,5,'케냐','나이로비')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Kuwait', 4,'Kuwait City',0x7f0700ff,6,'쿠웨이트','쿠웨이트')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Lebanon', 4,'Beirut',0x7f070100,6,'레바논','베이루트')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Lesotho', 4,'Maseru',0x7f070101,5,'레소토','마세루')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Liberia', 4,'Monrovia',0x7f070102,5,'라이베리아','몬로비아')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Libya', 4,'Tripoli',0x7f070103,5,'리비아','트리폴리')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Madagascar', 4,'Antananarivo',0x7f070104,5,'마다가스카르','안타나나리보')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Malawi', 4,'Lilongwe',0x7f070105,5,'말라위','릴롱궤')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Mali', 4,'Bamako',0x7f070106,5,'마리','바마코')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Mauritania', 4,'Nouakchott',0x7f070107,5,'모리타니','누악쇼트')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Mauritius', 4,'Port Louis',0x7f070108,5,'모리셔스','포트루이스')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Morocco', 4,'Rabat',0x7f070109,5,'모로코','라바트')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Mozambique', 4,'Maputo',0x7f07010a,5,'모잠비크','마푸투')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Namibia', 4,'Windhoek',0x7f07010b,5,'나미비아','빈트후크')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Niger', 4,' Niamey',0x7f07010c,5,'니제르','니아메')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Nigeria', 4,'Abuja',0x7f07010d,5,'나이지리아','아부자')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Qatar', 4,'Doha',0x7f07010e,6,'카타르','도하')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Republic of South Africa', 4,'Cape Town',0x7f07010f,5,'남아프리카 공화국','케이프타운')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Rwanda', 4,' Kigali',0x7f070110,5,'르완다','키갈리')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Saudi Arabia', 4,'Riyadh',0x7f070111,6,'사우디아라비아','리야드')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Senegal', 4,'Dakar',0x7f070112,5,'세네갈','다카르')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Somalia', 4,'Mogadishu',0x7f070113,5,'소말리아','모가디슈')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Republic of South Sudan', 4,'Juba',0x7f070114,5,'남수단','주바')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Sudan', 4,'Khartoum',0x7f070115,5,'수단','하르툼')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Syria', 4,'Damascus',0x7f070116,6,'시리아','다마스쿠스')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Togo', 4,'Lomé',0x7f070117,5,'토고','로메')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Tunisia', 4,'Tunis',0x7f070118,5,'튀니지','튀니스')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Turkey', 4,'Ankara',0x7f070119,6,'터키','앙카라')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('United Arab Emirates', 4,'Abu Dhabi',0x7f07011a,6,'아랍에미리트','아부다비')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Yemen', 4,'Sana',0x7f07011b,6,'예멘','사나')");
            sqLiteDatabase.execSQL("insert into cs_flag(country, continent, capital, res_id, coninent_num, country_kr, capital_kr) values('Zambia', 4,'Lusaka',0x7f070109,5,'잠비아','루사카')");

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
        if (continentNum == 0) {
            sql = "select _id, country, continent, capital, res_id from cs_flag";
        } else {
            sql = "select _id, country, continent, capital, res_id from cs_flag where continent = "+continentNum;
        }
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
