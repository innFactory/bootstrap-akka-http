package de.innfactory.bootstrap.utils
import java.util

class AutoValidate extends Authentication {
  override def validateToken(token: String): util.Map[String, AnyRef] = ???
}
