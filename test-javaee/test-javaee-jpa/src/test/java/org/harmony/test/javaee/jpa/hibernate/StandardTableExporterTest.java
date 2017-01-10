package org.harmony.test.javaee.jpa.hibernate;

import java.io.File;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;

import org.hibernate.boot.model.relational.AuxiliaryDatabaseObject;
import org.hibernate.boot.model.relational.Database;
import org.hibernate.boot.model.relational.Namespace;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.jdbc.internal.DDLFormatterImpl;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.hibernate.jpa.boot.spi.Bootstrap;
import org.hibernate.mapping.ForeignKey;
import org.hibernate.mapping.Table;
import org.hibernate.tool.schema.spi.Exporter;

/**
 * @author wuxii@foxmail.com
 */
public class StandardTableExporterTest {

    public static void main(String[] args) throws MalformedURLException, SQLException {
        File file = new File("src/main/resources/META-INF/persistence.xml");
        EntityManagerFactoryBuilderImpl builder = (EntityManagerFactoryBuilderImpl) Bootstrap.getEntityManagerFactoryBuilder(file.toURI().toURL(), "harmony", new HashMap());
        SessionFactoryImpl sessionFactory = (SessionFactoryImpl) builder.build();
        MetadataImplementor metadata = builder.getMetadata();
        Dialect dialect = sessionFactory.getJdbcServices().getDialect();
        Database database = metadata.getDatabase();
        Iterable<Namespace> namespaces = database.getNamespaces();
        Exporter<Table> tableExporter = dialect.getTableExporter();
        DDLFormatterImpl formatter = new DDLFormatterImpl();
        for (Namespace namespace : namespaces) {
            Collection<Table> tables = namespace.getTables();
            for (Table table : tables) {
                for (String sql : tableExporter.getSqlCreateStrings(table, metadata)) {
                    System.out.println(formatter.format(sql));
                }
            }
        }
        // ==========
        Collection<AuxiliaryDatabaseObject> auxiliaryDatabaseObjects = database.getAuxiliaryDatabaseObjects();
        Exporter<AuxiliaryDatabaseObject> auxiliaryDatabaseObjectExporter = dialect.getAuxiliaryDatabaseObjectExporter();
        for (AuxiliaryDatabaseObject ados : auxiliaryDatabaseObjects) {
            for (String sql : auxiliaryDatabaseObjectExporter.getSqlCreateStrings(ados, metadata)) {
                System.out.println(formatter.format(sql));
            }
        }
        // ========== 
        Exporter<ForeignKey> foreignKeyExporter = dialect.getForeignKeyExporter();
        for (Namespace namespace : namespaces) {
            Collection<Table> tables = namespace.getTables();
            for (Table table : tables) {
                for (ForeignKey fk : table.getForeignKeys().values()) {
                    for (String sql : foreignKeyExporter.getSqlCreateStrings(fk, metadata)) {
                        System.out.println(sql);
                    }
                }
            }
        }
        sessionFactory.close();
    }

}
