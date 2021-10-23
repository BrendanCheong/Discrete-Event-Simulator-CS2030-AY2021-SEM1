/open Question.java
/open LockQuestion.java
/open FillInBlank.java
/open MCQ.java
/open TFQ.java
/open Grader.java
/open FreeTenMarksGrader.java
/open OffByOneGrader.java
/open DummyGrader.java

Question q = new FillInBlank("Snow white and the ? dwarfs", new OffByOneGrader(7))
q.answer(7).lock().mark()
q.answer(6).lock().mark()
q.answer(8).lock().mark()
q.answer(5).lock().mark()
q.answer(10).lock().mark()
Question q = new FillInBlank("? blind mice", new FreeTenMarksGrader())
q.answer(3).lock().mark()
q.mark()
q.answer(0).lock().mark()