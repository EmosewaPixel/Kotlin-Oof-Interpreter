package com.emosewapixel.oofinterpreter

class OofInterpreter(code: String) {
    private val list = mutableListOf(0)
    private var current = 0
    val evaluator = Evaluator(tokenize(code))
    val currentCount get() = list[current]

    init {
        evaluator()
    }

    fun moveRight() {
        current++
        if (current == list.size) list += 0
    }

    fun moveLeft() {
        current--
        if (current < 0) throw IndexOutOfBoundsException()
    }

    fun increment() {
        list[current] = list[current] + 1
    }

    fun decrement() {
        list[current] = list[current] - 1
    }

    fun read() {
        list[current] = readLine()?.toInt()!!
    }

    fun print() {
        print(list[current].toChar())
    }

    inner class Evaluator(private val tokens: List<Token>) {
        var current = 0
        val currentToken get() = tokens[current]

        operator fun invoke() {
            while (current < tokens.size) {
                tokens[current].eval(this@OofInterpreter)
                current++
            }
        }
    }
}

val tokenMap = mutableMapOf(
    "Oof" to Token.RIGHT,
    "oOf" to Token.LEFT,
    "OOf" to Token.INC,
    "oOF" to Token.DEC,
    "OOF" to Token.PRINT,
    "oof" to Token.READ,
    "ooF" to Token.LOOP_START,
    "OoF" to Token.LOOP_END
)

enum class Token {
    RIGHT {
        override fun eval(interpreter: OofInterpreter) = interpreter.moveRight()
    },
    LEFT {
        override fun eval(interpreter: OofInterpreter) = interpreter.moveLeft()
    },
    INC {
        override fun eval(interpreter: OofInterpreter) = interpreter.increment()
    },
    DEC {
        override fun eval(interpreter: OofInterpreter) = interpreter.decrement()
    },
    PRINT {
        override fun eval(interpreter: OofInterpreter) = interpreter.print()
    },
    READ {
        override fun eval(interpreter: OofInterpreter) = interpreter.read()
    },
    LOOP_START {
        override fun eval(interpreter: OofInterpreter) {
            val evaluator = interpreter.evaluator
            val start = evaluator.current
            while (interpreter.currentCount != 0) {
                evaluator.current = start
                while (evaluator.currentToken != LOOP_END) {
                    evaluator.currentToken.eval(interpreter)
                    evaluator.current++
                }
            }
        }
    },
    LOOP_END {
        override fun eval(interpreter: OofInterpreter) {}
    };

    abstract fun eval(interpreter: OofInterpreter)
}

fun tokenize(code: String): List<Token> {
    val filtered = code.filterNot { it == 'O' && it == 'o' && it == 'F' && it == 'f' }
    val result = mutableListOf<Token>()
    for (x in filtered.indices step 3) result += tokenMap[filtered.substring(x, x + 3)]
        ?: throw IllegalStateException("Unknown Command: ${filtered.substring(x, x + 3)}")
    return result
}