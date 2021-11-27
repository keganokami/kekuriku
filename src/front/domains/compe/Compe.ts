import Events from "../events/Events";

export default class Compe {
    constructor(
        public adminId = "",
        public compeName = '',
        public compePlace = '',
        public compeDates = '',
        public compeGuidelinesFile = null,
        public compeEvent: Events[] = []
    ){}
}