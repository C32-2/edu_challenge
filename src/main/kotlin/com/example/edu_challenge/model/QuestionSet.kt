package com.example.edu_challenge.model

import jakarta.persistence.*

@Entity
@Table(name = "question_set")
class QuestionSet(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false, length = 255)
    var title: String,

    @Column(nullable = false)
    var difficulty: Int,

    @ManyToMany
    @JoinTable(
        name = "question_question_set",
        joinColumns = [JoinColumn(name = "question_set_id")],
        inverseJoinColumns = [JoinColumn(name = "question_id")]
    )
    var questions: MutableSet<Question> = mutableSetOf()
) {
    fun addQuestion(question: Question) {
        questions.add(question)
        question.questionSets.add(this)
    }

    fun removeQuestion(question: Question) {
        questions.remove(question)
        question.questionSets.remove(this)
    }
}
