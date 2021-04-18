/*
 * This file is generated by jOOQ.
 */
package generated.tables;


import generated.Emplotask;
import generated.tables.records.DatabasechangelogRecord;

import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row14;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Databasechangelog extends TableImpl<DatabasechangelogRecord> {

    private static final long serialVersionUID = -1773455843;

    /**
     * The reference instance of <code>emplotask.databasechangelog</code>
     */
    public static final Databasechangelog DATABASECHANGELOG = new Databasechangelog();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DatabasechangelogRecord> getRecordType() {
        return DatabasechangelogRecord.class;
    }

    /**
     * The column <code>emplotask.databasechangelog.id</code>.
     */
    public final TableField<DatabasechangelogRecord, String> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>emplotask.databasechangelog.author</code>.
     */
    public final TableField<DatabasechangelogRecord, String> AUTHOR = createField(DSL.name("author"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>emplotask.databasechangelog.filename</code>.
     */
    public final TableField<DatabasechangelogRecord, String> FILENAME = createField(DSL.name("filename"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>emplotask.databasechangelog.dateexecuted</code>.
     */
    public final TableField<DatabasechangelogRecord, LocalDateTime> DATEEXECUTED = createField(DSL.name("dateexecuted"), org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false), this, "");

    /**
     * The column <code>emplotask.databasechangelog.orderexecuted</code>.
     */
    public final TableField<DatabasechangelogRecord, Integer> ORDEREXECUTED = createField(DSL.name("orderexecuted"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>emplotask.databasechangelog.exectype</code>.
     */
    public final TableField<DatabasechangelogRecord, String> EXECTYPE = createField(DSL.name("exectype"), org.jooq.impl.SQLDataType.VARCHAR(10).nullable(false), this, "");

    /**
     * The column <code>emplotask.databasechangelog.md5sum</code>.
     */
    public final TableField<DatabasechangelogRecord, String> MD5SUM = createField(DSL.name("md5sum"), org.jooq.impl.SQLDataType.VARCHAR(35), this, "");

    /**
     * The column <code>emplotask.databasechangelog.description</code>.
     */
    public final TableField<DatabasechangelogRecord, String> DESCRIPTION = createField(DSL.name("description"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>emplotask.databasechangelog.comments</code>.
     */
    public final TableField<DatabasechangelogRecord, String> COMMENTS = createField(DSL.name("comments"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>emplotask.databasechangelog.tag</code>.
     */
    public final TableField<DatabasechangelogRecord, String> TAG = createField(DSL.name("tag"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>emplotask.databasechangelog.liquibase</code>.
     */
    public final TableField<DatabasechangelogRecord, String> LIQUIBASE = createField(DSL.name("liquibase"), org.jooq.impl.SQLDataType.VARCHAR(20), this, "");

    /**
     * The column <code>emplotask.databasechangelog.contexts</code>.
     */
    public final TableField<DatabasechangelogRecord, String> CONTEXTS = createField(DSL.name("contexts"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>emplotask.databasechangelog.labels</code>.
     */
    public final TableField<DatabasechangelogRecord, String> LABELS = createField(DSL.name("labels"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>emplotask.databasechangelog.deployment_id</code>.
     */
    public final TableField<DatabasechangelogRecord, String> DEPLOYMENT_ID = createField(DSL.name("deployment_id"), org.jooq.impl.SQLDataType.VARCHAR(10), this, "");

    /**
     * Create a <code>emplotask.databasechangelog</code> table reference
     */
    public Databasechangelog() {
        this(DSL.name("databasechangelog"), null);
    }

    /**
     * Create an aliased <code>emplotask.databasechangelog</code> table reference
     */
    public Databasechangelog(String alias) {
        this(DSL.name(alias), DATABASECHANGELOG);
    }

    /**
     * Create an aliased <code>emplotask.databasechangelog</code> table reference
     */
    public Databasechangelog(Name alias) {
        this(alias, DATABASECHANGELOG);
    }

    private Databasechangelog(Name alias, Table<DatabasechangelogRecord> aliased) {
        this(alias, aliased, null);
    }

    private Databasechangelog(Name alias, Table<DatabasechangelogRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> Databasechangelog(Table<O> child, ForeignKey<O, DatabasechangelogRecord> key) {
        super(child, key, DATABASECHANGELOG);
    }

    @Override
    public Schema getSchema() {
        return Emplotask.EMPLOTASK;
    }

    @Override
    public Databasechangelog as(String alias) {
        return new Databasechangelog(DSL.name(alias), this);
    }

    @Override
    public Databasechangelog as(Name alias) {
        return new Databasechangelog(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Databasechangelog rename(String name) {
        return new Databasechangelog(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Databasechangelog rename(Name name) {
        return new Databasechangelog(name, null);
    }

    // -------------------------------------------------------------------------
    // Row14 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row14<String, String, String, LocalDateTime, Integer, String, String, String, String, String, String, String, String, String> fieldsRow() {
        return (Row14) super.fieldsRow();
    }
}
