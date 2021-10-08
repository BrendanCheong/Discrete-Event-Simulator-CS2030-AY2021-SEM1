/open Question.java
/open LockQuestion.java
/open FillInBlank.java
/open MCQ.java
/open TFQ.java
/open Grader.java
/open FreeTenMarksGrader.java
/open OffByOneGrader.java
/open DummyGrader.java

TFQ tfq = new TFQ("An orange is blue.", "False")
tfq.answer("True")
tfq.answer("False")
tfq.answer("True").answer("True")
tfq.answer("False").answer("False")
MCQ mcq = tfq
mcq.answer(1)
tfq.answer(2)
mcq.answer("True")
tfq.answer(2).answer(1)