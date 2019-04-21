package spring.aspect;

public interface LoggedBean {
    void rememberHash(int number, int hash);

    int calculateHash(int number);
}
