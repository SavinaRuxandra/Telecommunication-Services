import {Client} from "../../clients/shared/client.model";
import {Subscription} from "../../subscriptions/shared/subscription.model";

export interface Contract {
  id: number;
  client: Client;
  subscription: Subscription;
  address: string
  date: Date
}
