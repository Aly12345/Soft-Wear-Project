/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package swpro;

/**
 *
 * @author Ali Emad
 */
public class Voucher implements Comparable<Voucher>{
    
    private String code;
    private String voucherValue;

    public Voucher(String code, String voucherValue) {
        this.code = code;
        this.voucherValue = voucherValue;
    }
    

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the voucherValue
     */
    public String getVoucherValue() {
        return voucherValue;
    }

    /**
     * @param voucherValue the voucherValue to set
     */
    public void setVoucherValue(String voucherValue) {
        this.voucherValue = voucherValue;
    }

    @Override
    public int compareTo(Voucher o) {
        if(Integer.parseInt(this.voucherValue) > Integer.parseInt(o.voucherValue)){
            return 1;
        }
        if(Integer.parseInt(this.voucherValue) < Integer.parseInt(o.voucherValue)){
            return -1;
        }
        return 0;
    }


}
