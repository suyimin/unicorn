package com.sunny.library.data;

import android.app.Application;

import com.sdk.DbManager;
import com.sdk.common.util.KeyValue;
import com.sdk.db.sqlite.SqlInfo;
import com.sdk.db.sqlite.WhereBuilder;
import com.sdk.db.table.DbModel;
import com.sdk.ex.DbException;
import com.sdk.util.LogUtil;
import com.sdk.x;

import java.util.List;

public class DBUtils {
    /*
        数据库操作步骤：
        1.DaoConfig 数据库的配置信息
        2.Database 获取数据库实例
        3.数据库操作:
          saveBindingId saveOrUpdate save 插入数据的3个方法(保存数据)
          replace 只有存在唯一索引时才有用 慎重
          delete操作的4种方法(删除数据)
          update操作的2种方法(修改数据)
          find操作6种方法(查询数据)
          dropTable 删除表
          addColumn 添加一列
          dropDb 删除数据库
     */

    public static DbManager.DaoConfig daoConfig = null; // 数据库配置

    private static String db_name;

    /**
     * 初始化数据库
     * @param app
     */
    public static void initDB(Application app, String db_names){
        db_name = db_names;
        x.Ext.init(app);
        setDaoConfig();
    }

    /**
     * 配置数据库
     */
    public static void setDaoConfig(){
        LogUtil.e("setDaoConfig db_name --------------= " + db_name);
        daoConfig = new DbManager.DaoConfig().setDbName(db_name) /* 设置数据库的名称 */
                .setDbVersion(1) /* 设置数据库的版本  */
                .setAllowTransaction(true) /* 设置允许开启事务  */
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() { /* 设置一个版本升级的监听方法 */
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                    }
                });
    }

    /************************************************* 数据库操作 ************************************************/
    /**
     * 删除库
     *
     * @return boolean
     */
    public static boolean dropDb() {
        boolean result = false;
        try {
            x.getDb(daoConfig).dropDb();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 往表里新添加一列
     * 新的表中必须定义了这个列的属性.
     *
     * @param table 表实体Bean
     * @param column 新增的列名
     * @return result
     * example: 
               当前表
        @Table(name="PersonTable") 
        public class PersonTable {
            @Column(name="id",isId=true,autoGen=true)
            private int id;     // 表ID
            
            @Column(name="name")
            private String name; // 名称
            
            @Column(name="length")
            private String length; // APP大小
            
            @Column(name="describe")
            private String describe; // 描述
       }
                  需要在此表增加多一列"path"
                  第一步：
                    修改当前表的实体Bean 
        @Table(name="PersonTable") 
        public class PersonTable {
            @Column(name="id",isId=true,autoGen=true)
            private int id;     // 表ID
            
            @Column(name="name")
            private String name; // 名称
            
            @Column(name="length")
            private String length; // APP大小
            
            @Column(name="describe")
            private String describe; // 描述
            
             @Column(name="path")
            private String path; // 此列为新增列.
       }
                   第二步：
         addColumn(PersonTable.class, "path");
     */
    public static boolean addColumn(Class<?> table, String column) {
        boolean result = false;
        try {
            x.getDb(daoConfig).addColumn(table, column);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除表
     *
     * @param table 表实体Bean
     * @return boolean
     */
    public static boolean dropTable(Class<?> table) {
        boolean result = false;
        try {
            x.getDb(daoConfig).dropTable(table);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /************************************************* 表数据操作 ************************************************/

    /**
     * 插入数据
     * 
     * @param entity 实体bean
     */
    public static boolean insertData(Object entity) {
        boolean result = false;
        try {
            x.getDb(daoConfig).save(entity); // 只能插入全新的实体类或实体类的List，如果出现主键冲突会报错
            //  x.getDb(daoConfig).saveOrUpdate(entity);      // 当一个实体或list里面的主键一样时会将当前主键对应的这条数据进行替换
            //  x.getDb(daoConfig).saveBindingId(entity);     // 存进去的数据如果当前表有主键与其他表主键进行绑定关联
            result = true;
        } catch (DbException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 插入数据
     *
     * @param strList 批量要执行的SQL语句
     */
    public static boolean exeSqlSave(List<String> strList) {
        boolean result = false;
        try {
            x.getDb(daoConfig).save(strList); // 只能插入全新的实体类或实体类的List，如果出现主键冲突会报错
            result = true;
        } catch (DbException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 根据SQL增删改操作
     * @param sql
     */
    public static void execNonQuery(String sql){
        try {
            x.getDb(daoConfig).execNonQuery(sql); // 只能插入全新的实体类或实体类的List，如果出现主键冲突会报错
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据id查询数据(只能查询一条数据)
     * 
     * @param table 实体bean
     * @param id 
     */
    public static <T> T query(Class<T> table, String id) {
        T entity = null;
        try {
            entity = x.getDb(daoConfig).findById(table, id);
        } catch (DbException e) {
            e.printStackTrace();
        }

        return entity;
    }

    /**
     * 根据实体bean查询数据(只能查询一条数据)
     * 
     * @param table 实体bean
     * @return T 
     */
    public static <T> T query(Class<T> table) {
        T entity = null;
        try {
            entity = x.getDb(daoConfig).findFirst(table);
        } catch (DbException e) {
            e.printStackTrace();
        }

        return entity;
    }

    /**
     * 根据实体bean查询当前表所有数据
     * 
     * @param table 实体bean
     */
    public static <T> List<T> queryAll(Class<T> table) {
        List<T> entity = null;
        try {
            entity = x.getDb(daoConfig).findAll(table);
        } catch (DbException e) {
            e.printStackTrace();
        }

        return entity;
    }

    /**
     * 根据条件查询数据(此方法要根据自己要求定制)
     * 
     * @param table 实体bean
     */
    public static <T> List<T> querySelector(Class<T> table) {
        List<T> entity = null;
        try {
            entity = x.getDb(daoConfig).selector(table).where("id", ">", 30).and("version", "=", "11").findAll();
            //           PersonTable test =  x.getDb(daoConfig).selector(PersonTable.class).where("id", "in", new int[]{1, 3, 6}).findFirst();
            //           long count =  x.getDb(daoConfig).selector(PersonTable.class).where("name", "LIKE", "w%").and("length", ">", 32).count();
            //           List<PersonTable> testList =  x.getDb(daoConfig).selector(PersonTable.class).where("id", "between", new String[]{"1", "5"}).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return entity;
    }

    /**
     * 根据SQL语句查询表第一行或全部的数据(获取值的方法，可以根据Model.getString("表中的列名"))
     * 
     * @param sql
     * @return DbModel
     * example: 
        String sql = "select * from 表名 where 列名=''";
        DbModel model = queryMDFirst(sql);
        int id = model.getInt("id");
        String length = model.getString("length");
     */
    public static <T> DbModel queryMDFirst(String sql) {
        DbModel model = null;
        try {
            model = x.getDb(daoConfig).findDbModelFirst(new SqlInfo(sql)); // 此方法只查询出第一条记录
        } catch (DbException e) {
            e.printStackTrace();
        }

        return model;
    }

    /**
     * 根据SQL语句查询表第一行或全部的数据(获取值的方法，可以根据Model.getString("表中的列名"))
     * 
     * @param sql
     * @return List<DbModel>
     * example: 
        String sql = "select * from 表名 where 列名=''";
        List<DbModel> models = queryMDFirst(sql);
        for(DbModel model : models){
            int id = model.getInt("id");
            String length = model.getString("length");
        }
     */
    public static <T> List<DbModel> queryMDAll(String sql) {
        List<DbModel> models = null;
        try {
            models = x.getDb(daoConfig).findDbModelAll(new SqlInfo(sql)); // 查询表中满足条件的所有数据
        } catch (DbException e) {
            e.printStackTrace();
        }

        return models;
    }

    /**
     * 根据ID，修改表中某列的值
     * 
     * @param entity 要修改的对象
     * @param column 列名(可以同时修改多列)
     * @return boolean 修改成功true, 失败false
     * example: 
        List<PersonTable> tableList = new ArrayList<PersonTable>();
        for(int i=0; i<10; i++){
            PersonTable table = new PersonTable();
            table.setName("person_2016_apk");
            table.setLength("4000");
            tableList.add(table);
        }
        String[] ncolumn = {"name", "length"};
        update(entity, ncolumn)
     */
    public static <T> boolean update(Object entity, String... column) {
        try {
            x.getDb(daoConfig).update(entity, column);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据ID，修改表中某列的值
     * @param table 表实体bean
     * @param whereBuilder 判断条件
     * @param nameValuePairs 列名和参数(可以修改多列)
     * @return boolean 修改成功true, 失败false
     * example:  KeyValue key1 = new KeyValue("name", "person_2016_apk");
                 KeyValue key2 = new KeyValue("length", "1024");
                 ...
                 update(PersonTable.class, WhereBuilder.b("name", "like", "persons_2016.apK"), key1, key2...);
     */
    public static <T> int update(Class<?> table, WhereBuilder whereBuilder, KeyValue... nameValuePairs) {
        int result = 0;
        try {
            result = x.getDb(daoConfig).update(table, whereBuilder, nameValuePairs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // select key1, key2 from table tt where name like 'persons_2016.apK'
        return result;
    }

    /**
     * 根据表的主键进行单条记录的删除.
     * 
     * @param table 表实体Bean
     * @param idValue 列ID值
     *  * example:
            int id = 100;
            deleteById(PersonTable.class, id);
     */
    public static boolean deleteById(Class<?> table, Object idValue) {
        boolean result = false;
        try {
            x.getDb(daoConfig).deleteById(table, idValue);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据实体bean进行对表里面的一条或多条数据进行删除 
     * 
     * @param entity 表实体bean
     * @return boolean 删除成功返回true, 否则false
     * example:
            PersonTable table = new PersonTable();
            table.setId(10);
            table.setLength("1000");
            delete(table);
     */
    public static boolean delete(Object entity) {
        boolean result = false;
        try {
            x.getDb(daoConfig).delete(entity);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据实体bean，删除当前表所有数据
     * 
     * @param entity 表实体bean
     * @return boolean 删除成功返回true, 否则false
     * example:
           delete(PersonTable.class);
     */
    public static boolean delete(Class<?> entity) {
        boolean result = false;
        try {
            x.getDb(daoConfig).delete(entity);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 根据where语句的条件进行删除操作 
     * 
     * @param table
     * @return int 删除条数，-1表示删除失败
     * example:
           delete(PersonTable.class, WhereBuilder.b("name", "like", "persons_2016.apK"));
     */
    public static int delete(Class<?> table, WhereBuilder whereBuilder) {
        int result = 0;
        try {
            result = x.getDb(daoConfig).delete(table, whereBuilder);
        } catch (Exception e) {
            e.printStackTrace();
            result = -1;
        }
        return result;
    }

    /**
     * 关闭数据库
     * 对同一个库的链接是单实例的, 一般不需要关闭它.
     */
    public static boolean close() {
        boolean result = false;
        try {
            x.getDb(daoConfig).close();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
