package com.stockinfo.excelentity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author f4cklangzi@gmail.com
 * @since 2020-05-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("stockinfo_table")
public class Table implements Serializable {

    private static final long serialVersionUID=1L;

    private String id;

    private String mc;

    private String gdzjpj;

    private String drbh;

    private String wrbh;

    private String esrbh;

    private String bzzf;

    private String lx;

    private LocalDateTime creationtime;

    private String ddgpdm;


}
