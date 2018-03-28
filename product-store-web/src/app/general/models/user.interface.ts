export interface User {
  id: string;
  email: string;
  userName: string;
  accountType: AccountType;
  userRole: UserRole;
}

export type AccountType = 'PREMIUM' | 'NORMAL';

export type UserRole = 'USER' | 'ADMIN';
