package me.richardcollins.economy.storage;

import java.sql.ResultSet;

public class DataManager {
	public MySQL core = null;

	public static String tableName = "profiles";

	public DataManager(MySQL c) {
		core = c;

		core.open();

		startDB();
	}

	public void startDB() {
		if (!core.tableExists(tableName)) {
			String query = "CREATE TABLE IF NOT EXISTS " + tableName + " ( `name` varchar(20) NOT NULL, `balance` double NOT NULL, PRIMARY KEY (`name`));";
			core.execute(query);
		}
	}

	public void updateBalance(String name, double bal) {
		if (!core.playerExists(name)) {
			addPlayer(name);
		}

		String query = "UPDATE " + tableName + " SET balance =  '" + bal + "' WHERE name = '" + name + "';";
		core.update(query);
	}

	public void addPlayer(String name) {
		String query = "INSERT INTO " + tableName + " (`name`,`balance`) VALUES ('" + name + "','20.0');";
		core.insert(query);
	}

	public Double getBalance(String name) {
		if (!core.playerExists(name)) {
			return 0.0;
		}

		String query = "SELECT * FROM " + tableName + " WHERE name = '" + name + "';";
		ResultSet res = core.select(query);

		try {
			if (res.next()) {
				String toRet = res.getString("balance");
				return Double.parseDouble(toRet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
