package com.robot.config;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.stereotype.Service;

import com.robot.utils.Log;

/**
 * Hbase 配置以及初始化Hbase 表
 * @author sloriac
 *
 */
@Service
public class HbaseConfig {
	private HbaseTemplate mHbaseTemplate;
	private List<String> mNameList;
	private List<String> mColumnList;

	public HbaseConfig() {
	}

	/**
	 * 该构造方法会在系统加载时候被调用,已经在spring中配置
	 * @param mHbaseTemplate
	 * @param mNameList
	 * @param mColumnList
	 */
	public HbaseConfig(HbaseTemplate mHbaseTemplate,
			List<String> mNameList, List<String> mColumnList) {
		super();
		this.mHbaseTemplate = mHbaseTemplate;
		this.mNameList = mNameList;
		this.mColumnList = mColumnList;
		//创建表
		createTables();
	}

	/**
	 * 创建Hbase表
	 */
	public void createTables() {
		try {
			HBaseAdmin hBaseAdmin = new HBaseAdmin(mHbaseTemplate.getConfiguration());
			int columnNumbers = mColumnList.size();
			int nameNumbers = mNameList.size();
			for (int i = 0; i < nameNumbers; i++) {
				// 如果表以及存在，不做操作
				if (hBaseAdmin.tableExists(mNameList.get(i))) {
				} else {
					HTableDescriptor tableDescriptor = new HTableDescriptor(
							TableName.valueOf(mNameList.get(i)));
					Log.log("创建表:"+ mNameList.get(i));
					// 添加列族
					for (int j = 0; j < columnNumbers; j++) {
						tableDescriptor.addFamily(new HColumnDescriptor(mColumnList.get(j)));
					}
					hBaseAdmin.createTable(tableDescriptor);
				}
			}
			hBaseAdmin.close();
		} catch (MasterNotRunningException e) {
			e.printStackTrace();
		} catch (ZooKeeperConnectionException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
