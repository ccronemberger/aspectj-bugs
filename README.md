# aspectj-bugs
Project to reproduce a few problems I found when using the AspectJ compiler and weaver.

By changing the configuration it is possible to reproduce problem in different scenarios.
The changes are to be done in two files:

- build.gradle - to select either weaving or compilation
- the class MyTargetAspect2 by changing the @Around annotation

In the class MyTargetAspect2 I have added comments about the results I get with each of the 3 cases.

Case #3 is the only one that looks correct for me.

Case #2 works in terms of number of interceptions but is clearly not correct in the number of join points applied (the logs appear only in the weaving case).

Case #1 is the worst case for both compilation and weaving: with weaving the code does not even run and with compilation the number of interceptions is completelly wrong and the stack trace is also much bigger.

I know that in the Case #1 we would get more interceptions because it would count 2 executions and 2 calls, but still it would be only 4 interceptions and not 12.

In order to modify the build.gradle file to use compilation instead of weaving one can uncomment the line with "aspectj.AspectjGradlePlugin", uncommend the "aspectpath" declaration, and finally comment the lines with "jvmArgs".
