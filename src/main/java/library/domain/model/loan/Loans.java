package library.domain.model.loan;

import java.util.Collections;
import java.util.List;

/**
 * 貸出のリスト
 */
public class Loans {
    List<Loan> list;

    public Loans(List<Loan> list) {
        this.list = list;
    }

    public int count() {
        return list.size();
    }

    public List<Loan> asList() {
        return Collections.unmodifiableList(list);
    }
}
