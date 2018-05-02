public class Student extends Person {
    private int OLD_MODULE_THRESHOLD = 2;
    private int MAX_MODULE_TOTAL = 6;
    private double NEW_MODULE_FEE = 525.0;
    private double OLD_MODULE_FEE = 110.0;

    private int moduleNew = 0;
    private int moduleOld = 0;
    private int moduleTotal = 0;
    private double balance = 0.0;
    private double amountPaid = 0.0;
    private double tuitionFee = 0.0;

    public boolean setNewModule(int newModule) {
        if(newModule + moduleOld <= MAX_MODULE_TOTAL && moduleOld <= OLD_MODULE_THRESHOLD) {
            moduleNew = newModule;
            moduleTotal = moduleNew + moduleOld;
            tuitionFee = (moduleOld * OLD_MODULE_FEE) + (moduleNew * NEW_MODULE_FEE);
        } else {
            return false;
        }
        return true;
    }

    public boolean setOldModule(int oldModule) {
        if(oldModule + moduleNew <= MAX_MODULE_TOTAL) {
            moduleOld = oldModule;
            moduleTotal = moduleNew + moduleOld;
            tuitionFee = (moduleOld * OLD_MODULE_FEE) + (moduleNew * NEW_MODULE_FEE);
        } else {
            return false;
        }
        return true;
    }

    public boolean feeDeposit(double depositAmount) {
        if(balance - depositAmount >= 0) {
            balance -= depositAmount;
            amountPaid += depositAmount;
        } else {
            return false;
        }
        return true;
    }

    public int getModuleNew() {
        return moduleNew;
    }

    public void setModuleNew(int moduleNew) {
        this.moduleNew = moduleNew;
    }

    public int getModuleOld() {
        return moduleOld;
    }

    public void setModuleOld(int moduleOld) {
        this.moduleOld = moduleOld;
    }
  
    public int getModuleTotal() {
        return moduleTotal;
    }

    public void setModuleTotal(int moduleTotal) {
        this.moduleTotal = moduleTotal;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public double getTuitionFee() {
        return tuitionFee;
    }

    public void setTuitionFee(double tuitionFee) {
        this.tuitionFee = tuitionFee;
    }
}
