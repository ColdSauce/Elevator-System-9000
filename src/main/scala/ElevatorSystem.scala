import ElevatorSystem.ElevatorMalfunctionError
import cats.data.Xor

/**
  * Created by campi on 10/25/2016.
  */
class ElevatorSystem(elevators: Vector[Elevator], listeners: Vector[(Elevator) => Unit]) {


  def getAllStatus: Vector[Elevator] = {
    elevators
  }

  def createPickup(floorNumber: Int): Elevator = {
    val elevatorToUse = elevators.reduce[Elevator]((acc, curr) => {
      Util.nearer(acc, curr, floorNumber)
    })

    Elevator(elevatorToUse.elevatorId, ElevatorState(elevatorToUse.elevatorState.currentFloor,
      elevatorToUse.elevatorState.goalNumbers :+ (floorNumber), elevatorToUse.elevatorState.isGoingUp))
  }


  def update(elevator: Elevator): Unit = {
    listeners.foreach(listener => listener(elevator))
  }

  def step: ElevatorSystem = {
    val newElevators = elevators.map(_.step)
    newElevators.foreach(elevator => update(elevator))
    new ElevatorSystem(newElevators, listeners)
  }

}

object ElevatorSystem {
  trait ElevatorMalfunctionError
  case object OutOfFuelError

  trait ElevatorSystemCreationError
  case object TooManyElevatorsError extends ElevatorSystemCreationError

  def createFromElevators(elevators: Vector[Elevator],
                          listeners: Vector[(Elevator) => Unit]):
                          Xor[ElevatorSystemCreationError, ElevatorSystem] = {
    if(elevators.length > 16) {
      Xor.left(TooManyElevatorsError)
    } else {
      Xor.right(new ElevatorSystem(elevators, listeners))
    }
  }
}
