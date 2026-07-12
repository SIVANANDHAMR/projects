CREATE OR REPLACE PROCEDURE run_monthly_payroll(p_month IN VARCHAR2) IS
BEGIN
  FOR r IN (SELECT emp_id, salary FROM employees) LOOP
    -- Simple calculation: tax = 10% , deductions = 5%
    INSERT INTO payrolls (payroll_id, emp_id, payroll_month, gross_pay, tax, deductions, net_pay)
    VALUES (seq_payroll.NEXTVAL, r.emp_id, p_month, r.salary, r.salary * 0.10, r.salary * 0.05, r.salary * 0.85);
  END LOOP;
  COMMIT;
END;
/
