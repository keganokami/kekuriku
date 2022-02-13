import Events from "../events/Events";

export default class Compe {
  constructor(
    public compeName = '',
    public compePlace = '',
    public compeDates = '',
    public compeGuidelinesFile = null,
    public compeFeeType = '',
    public compeParticipationFee = 0,
    public settingMaxParticipation = false,
    public compeMaxParticipation = 0,
    public compeEvent: Events[] = []
  ) {}
}