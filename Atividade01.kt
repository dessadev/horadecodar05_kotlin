import kotlin.system.exitProcess

var saldo = 100.5f
val extrato = mutableListOf(
    "Depósito inicial de 100.5",
    "Compra no supermercado de 45.75",
    "Depósito de 200.0",
    "Compra online de 30.0"
)
const val Senha = "3589"

fun main() {
    print("Digite seu nome: ")
    val nome = readln()
    println("Olá $nome, é um prazer ter você por aqui!")
    inicio(nome)
}

fun inicio(nome: String) {
    println("Escolha uma opção:")
    println("1. Ver saldo")
    println("2. Ver extrato")
    println("3. Fazer saque")
    println("4. Fazer depósito")
    println("5. Fazer transferência")
    println("6. Sair")

    val escolha = readln().toIntOrNull()

    when (escolha) {
        1 -> verSaldo(nome)
        2 -> verExtrato(nome)
        3 -> fazerSaque(nome)
        4 -> fazerDeposito(nome)
        5 -> fazerTransferencia(nome)
        6 -> sair(nome)
        else -> erro(nome)
    }
}

fun verificarSenha(): Boolean {
    print("Digite sua senha: ")
    val senha = readln()
    return senha == Senha
}

fun verSaldo(nome: String) {
    if (verificarSenha()) {
        println("Seu saldo atual é: $saldo")
    } else {
        println("Senha incorreta. Tente novamente.")
    }
    inicio(nome)
}

fun verExtrato(nome: String) {
    if (verificarSenha()) {
        println("Extrato:")
        if (extrato.isEmpty()) {
            println("Nenhuma transação registrada.")
        } else {
            extrato.forEach { println(it) }
        }
    } else {
        println("Senha incorreta. Tente novamente.")
    }
    inicio(nome)
}

fun fazerDeposito(nome: String) {
    if (verificarSenha()) {
        print("Qual o valor para depósito? ")
        val deposito = readln().toFloatOrNull()

        if (deposito == null || deposito <= 0) {
            println("Operação não autorizada. O valor deve ser maior que zero.")
        } else {
            saldo += deposito
            extrato.add("Depósito de $deposito")
            println("Depósito de $deposito realizado com sucesso.")
        }
    } else {
        println("Senha incorreta. Tente novamente.")
    }
    inicio(nome)
}

fun fazerSaque(nome: String) {
    if (verificarSenha()) {
        print("Qual o valor para saque? ")
        val saque = readln().toFloatOrNull()

        if (saque == null || saque <= 0) {
            println("Operação não autorizada. O valor deve ser maior que zero.")
        } else if (saque > saldo) {
            println("Operação não autorizada. Saldo insuficiente.")
        } else {
            saldo -= saque
            extrato.add("Saque de $saque")
            println("Saque de $saque realizado com sucesso.")
        }
    } else {
        println("Senha incorreta. Tente novamente.")
    }
    inicio(nome)
}

fun fazerTransferencia(nome: String) {
    if (verificarSenha()) {
        print("Digite o número da conta para transferência: ")
        val conta = readln().toIntOrNull()

        if (conta == null) {
            println("Operação não autorizada. O número da conta deve ser um número válido.")
            fazerTransferencia(nome)
            return
        }

        print("Qual o valor para transferência? ")
        val transferencia = readln().toFloatOrNull()

        if (transferencia == null || transferencia <= 0) {
            println("Operação não autorizada. O valor deve ser maior que zero.")
        } else if (transferencia > saldo) {
            println("Operação não autorizada. Saldo insuficiente.")
        } else {
            saldo -= transferencia
            extrato.add("Transferência de $transferencia para a conta $conta")
            println("Transferência de $transferencia para a conta $conta realizada com sucesso.")
        }
    } else {
        println("Senha incorreta. Tente novamente.")
    }
    inicio(nome)
}

fun erro(nome: String) {
    println("Por favor, informe um número válido de 1 a 6.")
    inicio(nome)
}

fun sair(nome: String) {
    println("$nome, foi um prazer ter você por aqui!")
    exitProcess(0)
}
