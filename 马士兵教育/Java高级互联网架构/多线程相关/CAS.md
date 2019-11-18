
CAS： 无锁优化、乐观锁

CAS：CPU原语支持，CPU的读屏障/写屏障保障了CAS的比较和赋值的原子性。

java.util.concurrent.atomic.AtomicXXX：内部实现使用的是CAS
