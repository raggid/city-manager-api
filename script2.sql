declare
    val number;
    dat date;
    cout number := 1;
begin
    for i in 1..1000 loop
        val := 9 - floor(i/100.01);
        select sysdate-val into dat from dual;
        insert into EXAME_NF values (i, i, dat, 0);
        for j in 1..3 loop
            insert into EXAME_ITEMNF values(cout, i, cout, 1, i*cout);
            cout := cout + 1;
        end loop;
    end loop;
end;