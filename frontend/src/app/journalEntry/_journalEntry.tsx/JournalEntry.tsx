import type { JournalEntry } from "@/api/types";
import { JournalEntryType } from "@/api/types";

type JournalEntryProps = {
  journalEntries: JournalEntry[];
};

export default function JournalEntry({ journalEntries }: JournalEntryProps) {
  return (
    <>
      <div className="w-full h-14 border-b border-gray-300 flex justify-between items-center">
        <h1 className="mx-10 text-lg leading-14 text-gray-700 font-normal">
          仕訳一覧
        </h1>
      </div>
      <table className="w-full border-collapse">
        <thead>
          <tr className="bg-gray-100 font-bold text-xs border-b border-gray-300">
            <td className="p-4">日付</td>
            <td className="p-4">仕訳No.</td>
            <td className="p-4">部署</td>
            <td className="p-4">借方勘定科目</td>
            <td className="p-4">借方金額</td>
            <td className="p-4">貸方勘定科目</td>
            <td className="p-4">貸方金額</td>
          </tr>
        </thead>
        <tbody>
          {journalEntries.map((journalEntry: JournalEntry) => {
            const debitDetails = journalEntry.details.filter(
              (detail) => detail.journalEntryType === JournalEntryType.DEBIT
            );
            const creditDetails = journalEntry.details.filter(
              (detail) => detail.journalEntryType === JournalEntryType.CREDIT
            );

            const maxRows = Math.max(debitDetails.length, creditDetails.length);

            return Array.from({ length: maxRows }, (_, index) => (
              <tr
                key={`${journalEntry.number.value}-${index}`}
                className="border-b border-gray-300"
              >
                <td className="p-3">
                  {index === 0 ? journalEntry.date.value : ""}
                </td>
                <td className="p-3">
                  {index === 0 ? journalEntry.number.value : ""}
                </td>
                <td className="p-3">
                  {index === 0 ? journalEntry.department.name.value : ""}
                </td>
                <td className="p-3">
                  {debitDetails[index]?.account.name.value || ""}
                </td>
                <td className="p-3">
                  {debitDetails[index]?.amount.value.toLocaleString() || ""}
                </td>
                <td className="p-3">
                  {creditDetails[index]?.account.name.value || ""}
                </td>
                <td className="p-3">
                  {creditDetails[index]?.amount.value.toLocaleString() || ""}
                </td>
              </tr>
            ));
          })}
        </tbody>
      </table>
    </>
  );
}
