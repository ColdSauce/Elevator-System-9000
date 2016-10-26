/**
  * Created by campi on 10/25/2016.
  */
object Util {
  def nearer(one: Elevator, two: Elevator, targetGoal: Int): Elevator = {
    val oneDiff = Math.abs(one.elevatorState.currentFloor - targetGoal)
    val twoDiff = Math.abs(two.elevatorState.currentFloor - targetGoal)
    if(oneDiff < twoDiff) {
      one
    } else {
      two
    }
  }
}
