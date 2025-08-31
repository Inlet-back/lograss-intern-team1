import JournalEntry from "./_journalEntry.tsx/JournalEntry";
import { getJournalEntries } from "@/api/journalEntries";

export default async function JournalEntries() {
  const journalEntries = await getJournalEntries();
  return <JournalEntry journalEntries={journalEntries} />;
}
