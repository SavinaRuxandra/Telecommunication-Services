import {IDCard} from "../../idCards/shared/idCard.model";

export interface Client {
  id: number;
  idCard: IDCard;
  name: string;
  email: string;
}
