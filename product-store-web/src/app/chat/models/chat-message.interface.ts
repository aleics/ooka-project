export interface ChatMessage {
  channelId: string;
  sender: string;
  message: string;
  id?: string;
  creationTimestamp?: any;
}
