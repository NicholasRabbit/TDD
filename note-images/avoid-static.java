
public class Database {
	public static Object findById(String id) {
		// fetch an object from the database,
		// returning a null if the id is not found
	}
	public static boolean objectExists(String id) {
		return (findById(id) != null);
	}
}

public class TestDatabase {
	@Test
	public void testObjectExists() throws Exception {
		// How can I fake findById() to return
		// "true" or "false" as I wish?
		assertTrue(Database.objectExists("123"));
	}
}
