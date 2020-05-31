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
@TableName("stockinfo_institutionstable")
public class Institutionstable implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String mc;

    private String ltpj;

    private String bsz;

    private String qqjgcgsl;

    private String bsqbh;

    private String zzjds;

    private String lx;

    private LocalDateTime creationtime;

    private String ddgpdm;


}
