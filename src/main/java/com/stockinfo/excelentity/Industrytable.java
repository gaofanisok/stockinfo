package com.stockinfo.excelentity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@TableName("stockinfo_industrytable")
public class Industrytable implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String mc;

    private String gn;

    private String ph;

    private String ltpj;

    private String ltsz;

    private String ccjgsl;

    private LocalDateTime creationtime;

    private String lx;

    private String ddgpdm;


}
