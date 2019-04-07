import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

object AESCrypt {
    fun encrypt(input: String, passwd: String): String {
        //1.����cipher����
        val cipher = Cipher.getInstance("AES")
        //2.��ʼ��cipher��
        cipher.init(Cipher.ENCRYPT_MODE, SecretKeySpec(passwd.toByteArray(), "AES"))
        //3.���ܣ�
        return Base64.getEncoder().encodeToString(cipher.doFinal(input.toByteArray()))
    }

    fun decrypt(input: String, passwd: String): ByteArray {
        //1.����cipher����
        val cipher = Cipher.getInstance("AES")
        //2.��ʼ��cipher��
        cipher.init(Cipher.DECRYPT_MODE, SecretKeySpec(passwd.toByteArray(), "AES"))
        //3.���ܣ�
        return cipher.doFinal(Base64.getDecoder().decode(input))
    }
}

fun en() {
    println("��������Ҫ���ܵ�����:")
    val input = readLine()!!
    println("������������16λ����:")
    val passwd = readLine()!!
    val encrypt = AESCrypt.encrypt(input, passwd)
    println("��������ܺ���ļ���:")
    val br = BufferedWriter(FileWriter("${readLine()}"))
    br.write(encrypt)
    br.close()
}

fun de() {
    println("��������ļ���:")
    val input = BufferedReader(FileReader("${readLine()}")).readLine()
    println("�������������:")
    val passwd = readLine()!!
    println("AES���ܣ�" + String(AESCrypt.decrypt(input, passwd)))
}

fun main() {
    while (true) {
        println("��������1  ��������2")
        val mode = readLine()
        when (mode) {
            "1" -> en()
            "2" -> de()
        }
    }
}