
package jspdemoo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class Main {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();  // Obtain EntityManager
        EntityTransaction transaction = em.getTransaction();  // Get the transaction object
        
        try {
            transaction.begin();  // Start the transaction
            
            // Create multiple Employee objects
            Employee emp1 = new Employee("Alice", "Development", 800,20);
            Employee emp2 = new Employee("Bob", "Marketing", 650,30);
            Employee emp3 = new Employee("Charlie", "HR", 700,25);
            Employee emp4 = new Employee("Diana", "Finance", 850,35);
            Employee emp5 = new Employee("Eve", "Development", 900,40);
            
            // Persist the Employee objects
            em.persist(emp1);
            em.persist(emp2);
            em.persist(emp3);
            em.persist(emp4);
            em.persist(emp5);
            
            transaction.commit();  // Commit the transaction
            System.out.println("Employees created and persisted successfully!");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Rollback in case of error
            }
            e.printStackTrace();
        } finally {
            em.close();  // Close the EntityManager
            JPAUtil.close();  // Close the JPA utility (e.g., close EntityManagerFactory)
        }
    }
}
