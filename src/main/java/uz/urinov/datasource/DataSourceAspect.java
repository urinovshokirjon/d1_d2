package uz.urinov.datasource;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class DataSourceAspect {

    // Pointcut for any method starting with "get", "find", "read", etc.
    @Pointcut("execution(* uz.urinov..repository..*.find*(..)) || execution(* uz.urinov..repository..*.get*(..)) ")
//    @Pointcut("execution(* org.springframework.data.repository.CrudRepository+.find*(..))")
    public void readOperation() {
    }

    @Before("readOperation()")
    public void setReadDataSource() {
        TransactionRoutingDataSource.setReadonlyDataSource(true);
    }

    // Pointcut for any method starting with "save", "insert", "update", etc.
    @Pointcut("execution(* uz.urinov..repository..*.*(..)) && !(execution(* uz.urinov..repository..*.find*(..)) || execution(* uz.urinov..repository..*.get*(..)))")
//    @Pointcut("execution(* org.springframework.data.repository.CrudRepository+.save*(..))")
    public void writeOperation() {
    }

    @Before("writeOperation()")
    public void setWriteDataSource() {
        TransactionRoutingDataSource.setReadonlyDataSource(false);
    }
}
