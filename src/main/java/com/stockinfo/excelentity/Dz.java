package com.stockinfo.excelentity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2020-05-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("stockinfo_dz")
public class Dz implements Serializable {

    private static final long serialVersionUID=1L;

    private String id;

    private String plid;

    private String sfdz;

    private String dzid;


}
