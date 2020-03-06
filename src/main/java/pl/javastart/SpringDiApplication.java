package pl.javastart;

import com.example.springaop.SpringAopApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import pl.javastart.model.Book;
import pl.javastart.service.BookRepository;
import pl.javastart.service.GenericRepository;

@Configuration
@ComponentScan
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringDiApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringDiApplication.class);

//        GenericRepository<String, Book> repo = ctx.getBean(GenericRepository.class);
        BookRepository repo = ctx.getBean(BookRepository.class);
        repo.add(new Book("1234567890123", "Pierwsza", "Pierwszy autor"));
        repo.add(new Book("2345678901234", "Druga", "Drugi autor"));
        repo.add(new Book("3456789012345", "Trzecia", "Trzeci autor"));
//        repo.add(null);
        Book book = repo.get("1234567890123");
        System.out.println(book);

        ctx.close();
    }
}
