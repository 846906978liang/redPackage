package neau

import scala.io.StdIn
import scala.util.Random

object redPackage {
  //
  def randomP(remind:Int)={
    var r=0
    do{
      r=Random.nextInt(remind)//防止过大,过小
    }while(r>remind*0.5 || r<1)
    r
  }
  def main(args: Array[String]): Unit = {
    var redMoney:Double=StdIn.readLine("请输入红包金额").toFloat
    var redNum:Int=StdIn.readLine("请输入红包数量").toInt
    var remind:Int=(redMoney*100).toInt
    var sum:Double=0
//    var a:mutable.ArrayBuffer[Any] = new mutable.ArrayBuffer[Any]()

    //包红包
    var z=new Array[Double](redNum)
    for(i <- Range(0,redNum-1)){
      val q:Int=randomP(remind)
//      println(q)
      remind=remind-q
      z(i)=q/100.0
    }
    z(redNum-1)=remind/100.0
//    for(i <- Range(0,redNum)){
//      println(z(i))
//    }
    val result=Random.shuffle(z.toList)//打乱红包顺序

    //抢红包
    var maxMoney:Double=0
    var lucky:String=null
    var A:Map[String,Double] = Map()
    var i=0
    while(i<redNum){
      var name:String=StdIn.readLine("请输入姓名")

      //不能重复抢红包
      if(A.contains(name)){
        println(s"你已经抢过了哦，金额为${A(name)}元")
      }
      else {
        A+=(name->result(i))

        //幸运星
        if(maxMoney<result(i)){
          maxMoney=result(i)
          lucky=name
        }
        println("恭喜你抢到"+result(i)+"元")
        i=i+1
      }

    }
    println("幸运星是: "+lucky+"金额为: "+A(lucky))
}
}