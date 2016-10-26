/**
  * Created by campi on 10/25/2016.
  */
object Util {
  /**
    * nearer allows one to pick which elevator is better. The first or the second.
    * @param one
    * @param two
    * @param targetGoal
    * @return
    */
  def nearer(one: Elevator, two: Elevator, targetGoal: Int): Elevator = {
    if(one.elevatorState.isGoingUp && one.elevatorState.currentFloor < targetGoal) {
      two
    } else if(two.elevatorState.isGoingUp && two.elevatorState.currentFloor > targetGoal) {
      one
    } else {
      val oneDiff = Math.abs(one.elevatorState.currentFloor - targetGoal)
      val twoDiff = Math.abs(two.elevatorState.currentFloor - targetGoal)
      if(oneDiff < twoDiff) {
        one
      } else {
        two
      }
    }

  }
}
