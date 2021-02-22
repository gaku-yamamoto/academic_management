// バリデーション:工程や方法が正しいかどうかを検証する
package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Report;
import utils.DBUtil;

public class ReportValidator {
    public static List<String> validate(Report r, Boolean nameduplicatecheckflag) {
        List<String> errors = new ArrayList<String>();

        String name_error = validateName(r.getName(), nameduplicatecheckflag);
        if(!name_error.equals("")) {
            errors.add(name_error);
        }
        return errors;
    }

    // 論文題名の必須入力チェック
    private static String validateName(String name, Boolean nameduplicatecheckflag) {
        // 必須入力チェック
        if(name == null || name.equals("")) {
            return "題名を入力してください。";
        }

        // すでに登録されている論文名との重複チェック
        if(nameduplicatecheckflag) {
            EntityManager re = DBUtil.createEntityManager();
            long reports_count = (long)re.createNamedQuery("checkRegisteredName", Long.class)
                                           .setParameter("name", name)
                                             .getSingleResult();
            re.close();
            if(reports_count > 0) {
                return "入力された論文名の情報はすでに存在しています。";
            }
        }

        return "";
    }
}