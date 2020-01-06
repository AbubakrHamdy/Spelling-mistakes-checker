package sample;

import Not_Used.ConfirmBox2;

import static sample.Main.araabc;

public class ConfirmBoxsFunctions {
    public void SaveLabelToDBADDConfirmBoxMP(boolean bool) {
        if (bool == true) {
            if (araabc) {
                boolean answer = ConfirmBox2.display("اضافة خاطئة", "يرجى اضافة اسم الاجراء");
            } else {
                boolean answer2 = ConfirmBox2.display("Insert", "Please enter process name");
            }
        } else {
            if (araabc) {
                boolean answer = ConfirmBox2.display("اضافة صحيحة", "تم اضافة اجراءات العمل");
            } else {
                boolean answer = ConfirmBox2.display("Insert", "Procedure Added");
            }
        }
    }

    public void SaveLabelToDBUpdateConfirmBoxMP(boolean bool) {
        if (bool == true) {
            if (araabc) {
                boolean answer = ConfirmBox2.display("تعديل خاطئ", "يجب اضافة اسم الاجراء");
            } else {
                boolean answer = ConfirmBox2.display("Update", "Please add Procedure Name");
            }
        } else {
            if (araabc) {
                boolean answer = ConfirmBox2.display("تعديل ناجح", "تم تعديل اجراءات العمل ");
            } else {
                boolean answer = ConfirmBox2.display("Update", "Main Procedure Updated");
            }
        }
    }
    public void SaveLabelToDBUpdateConfirmBoxEM(boolean bool) {
        if (bool == true) {
            if (araabc) {
                boolean answer = ConfirmBox2.display("تعديل خاطئ", "يجب اضافة اسم الوظيفة");
            } else {
                boolean answer = ConfirmBox2.display("Update", "JD can't be updated. Please insert Job Title");
            }
        } else {
            if (araabc) {
                boolean answer = ConfirmBox2.display("تعديل ناجح", "تم تعديل بيانات الموظف");
            } else {
                boolean answer = ConfirmBox2.display("Update", "Employee Updated");
            }
        }
    }
    public void SaveLabelToDBADDConfirmBoxEM(boolean bool) {
        if (bool == true) {
            if (araabc) {
                boolean answer = ConfirmBox2.display("اضافة خاطئة", "يجب اضافة بيانات الوظيفة");
            } else {
                boolean answer = ConfirmBox2.display("Insert", "Please Enter a Job Title to Add Employee");
            }
        } else {
            if (araabc) {
                boolean answer = ConfirmBox2.display("اضافة صحيحة", "تم اضافة بيانات الموظف");
            } else {
                boolean answer = ConfirmBox2.display("Insert", "Employee Added");
            }

        }
    }
}
