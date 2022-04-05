package ru.gb;

import ru.gb.persist.Product;
import ru.gb.persist.ProductRepositoryImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class BootstrapListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
        productRepository.insert(new Product("Milk", BigDecimal.valueOf(95.99)));
        productRepository.insert(new Product("Bread", BigDecimal.valueOf(25.50)));
        productRepository.insert(new Product("Meat", BigDecimal.valueOf(325.60)));
        productRepository.insert(new Product("Fish", BigDecimal.valueOf(225.55)));
        productRepository.insert(new Product("Cheese", BigDecimal.valueOf(235.80)));
        productRepository.insert(new Product("Sausage", BigDecimal.valueOf(175.00)));
        productRepository.insert(new Product("Sugar", BigDecimal.valueOf(85.00)));
        productRepository.insert(new Product("Salt", BigDecimal.valueOf(45.00)));
        productRepository.insert(new Product("Sweets", BigDecimal.valueOf(353.60)));
        productRepository.insert(new Product("Tea", BigDecimal.valueOf(125.25)));
        productRepository.insert(new Product("Egg", BigDecimal.valueOf(99.99)));
        productRepository.insert(new Product("Butter", BigDecimal.valueOf(180.60)));
        productRepository.insert(new Product("Soup", BigDecimal.valueOf(86.50)));

        sce.getServletContext().setAttribute("productRepository", productRepository);
    }
}
