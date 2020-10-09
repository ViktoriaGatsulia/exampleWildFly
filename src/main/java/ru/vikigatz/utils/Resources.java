package ru.vikigatz.utils;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

/**
 * Класс для дополнительных ресурсов
 *
 * @author ViktoriaGatsulia
 * @version 1.0
 */
public class Resources {

    /**
     * Менеджер сущностей
     */
    @Produces
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Создание переменной логирования
     *
     * @param injectionPoint - точка вызова лога
     * @return - имя класса, в котором был вызван лог
     */
    @Produces
    public Logger produceLog(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

}
