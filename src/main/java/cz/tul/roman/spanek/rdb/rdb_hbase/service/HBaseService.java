package cz.tul.roman.spanek.rdb.rdb_hbase.service;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Service;

@Service
public class HBaseService {

    private final Connection connection;

    public HBaseService(Connection connection) {
        this.connection = connection;
    }

    public void writeStudent(String rowKey, String name, String age) throws Exception {
        Table table = connection.getTable(TableName.valueOf("students"));
        Put put = new Put(Bytes.toBytes(rowKey));
        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"), Bytes.toBytes(name));
        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("age"), Bytes.toBytes(age));
        table.put(put);
        table.close();
    }

    public String readStudent(String rowKey) throws Exception {
        Table table = connection.getTable(TableName.valueOf("students"));
        Get get = new Get(Bytes.toBytes(rowKey));
        Result result = table.get(get);
        String name = Bytes.toString(result.getValue(Bytes.toBytes("info"), Bytes.toBytes("name")));
        String age = Bytes.toString(result.getValue(Bytes.toBytes("info"), Bytes.toBytes("age")));
        table.close();
        return "Name: " + name + ", Age: " + age;
    }
}