import Events from "../events/Events";

export default class CompeResponse {
    constructor(
        public compeId = "",
        public compeName = '',
        public compePlace = '',
        public compeDates = '',
        public compeGuidelinesFile = null,
        public compeEvent: Events[] = []
    ){}
}