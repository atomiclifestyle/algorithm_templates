package LLD;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

//Here ElevatorLLD is Building
public class ElevatorLLD {
        // Building
        // │
        // ├── ElevatorController
        // │
        // ├── Elevator                 
        // │
        // ├── Floor
        // │
        // └── Request

    //Enums
    enum Direction {
        UP,
        DOWN,
        IDLE
    }

    enum ElevatorState {
        MOVING,
        IDLE,
        STOPPED,
        MAINTENANCE
    }

    class Request {
        int floor;
        Direction direction;

        Request(int floor, Direction dir){}
    }

    class Floor {
        int floorNo;

        // Buttons - External Requests
        void pressUp(){}
        void pressDown(){}
    }

    // Main/Building class uses this to operate the Elevator

    class ElevatorController {
        List<Elevator> elevators;
        List<Floor> floors;

        public void addExternalRequest(int elevatorId, int floorNumber, Direction direction) {
            Elevator selectedElevator = getElevatorById(elevatorId);
            if (selectedElevator != null) {
                selectedElevator.addRequest(new Request(floorNumber, direction));
            } else {
                System.out.println("No elevator available for floor " + floorNumber);
            }
        }

        public void addInternalRequest(int elevatorId, int floorNumber) {
            Elevator elevator = getElevatorById(elevatorId);
            Direction direction = floorNumber > elevator.currentFloor
                    ? Direction.UP
                    : Direction.DOWN;

            elevator.addRequest(new Request(floorNumber, direction));
        }

        private Elevator getElevatorById(int elevatorId) {
            for (Elevator elevator : elevators) {
                if (elevator.id == elevatorId)
                    return elevator;
            }
            return null;
        }

        void submitRequest(Request request){
            Elevator e = findBestElevator(request);
            e.addRequest(request);
        }

        Elevator findBestElevator(Request request){
            //...
            return null;
        }
    }

    class Elevator {
        int id;
        int currentFloor;
        Direction direction;
        ElevatorState state;

        Queue<Request> requests;

        void setDirection(Direction dir){}

        //Scheduling Stratergy
        int getNextStop(Elevator elevator) {
            // ACCEPTS REQUESTS ONLY IN THE CURRENT DIRECTION - SCAN Algorithm
            Direction elevatorDirection = elevator.direction;
            int currentFloor = elevator.currentFloor;


            if (requests.isEmpty())
                return currentFloor;


            // Priority queues to handle requests in up and down directions
            PriorityQueue<Request> upQueue = new PriorityQueue<>(); // Min-heap for upward requests
            PriorityQueue<Request> downQueue = new PriorityQueue<>((a, b) -> b.floor - a.floor); // Max-heap for downward requests


            // Categorize requests based on their relative position to the current floor
            while (!requests.isEmpty()) {
                Request elevatorRequest = requests.poll();
                int floor = elevatorRequest.floor;
                if (floor > currentFloor)
                    upQueue.add(elevatorRequest);
                else
                    downQueue.add(elevatorRequest);
            }


            // Handle the case when the elevator is IDLE
            if (elevatorDirection == Direction.IDLE) {
                // Determine the nearest request and set direction accordingly
                int nearestUpwardRequest =
                        upQueue.isEmpty() ? -1 : upQueue.peek().floor;
                int nearestDownwardRequest =
                        downQueue.isEmpty() ? -1 : downQueue.peek().floor;


                if (nearestUpwardRequest == -1) {
                    elevator.setDirection(Direction.DOWN);
                    return downQueue.poll().floor;
                } else if (nearestDownwardRequest == -1) {
                    elevator.setDirection(Direction.UP);
                    return upQueue.poll().floor;
                } else {
                    // Choose the closest request
                    if (Math.abs(nearestUpwardRequest - currentFloor)
                            < Math.abs(nearestDownwardRequest - currentFloor)) {
                        elevator.setDirection(Direction.UP);
                        return upQueue.poll().floor;
                    } else {
                        elevator.setDirection(Direction.DOWN);
                        return downQueue.poll().floor;
                    }
                }
            }


            // Handle movement in the UP direction
            if (elevatorDirection == Direction.UP) {
                return !upQueue.isEmpty() ? upQueue.poll().floor
                        : switchDirection(elevator);
            }
            // Handle movement in the DOWN direction
            else {
                return !downQueue.isEmpty() ? downQueue.poll().floor
                        : switchDirection(elevator);
            }
        }

        int switchDirection(Elevator elevator){ return 0; }

        void addRequest(Request request){  }
    }

}
