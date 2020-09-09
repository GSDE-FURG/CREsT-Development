using System;
using System.Collections.Generic;
using System.Text;

namespace CSharpJExcel.Interop
	{
	public class TreeSet<T> : ICollection<T>
		{
		private Dictionary<T, T> _container;

		public TreeSet()
			{
			_container = new Dictionary<T,T>();
			}

		public TreeSet(IEqualityComparer<T> comparer)
			{
			_container = new Dictionary<T,T>(comparer);
			}


		#region ICollection<T> Members

		public void Add(T item)
			{
			_container.Add(item, item);
			}

		public void Clear()
			{
			_container.Clear();
			}

		public bool Contains(T item)
			{
			return _container.ContainsKey(item);
			}

		public void CopyTo(T[] array, int arrayIndex)
			{
			_container.Keys.CopyTo(array, arrayIndex);
			}

		public int Count
			{
			get
				{
				return _container.Count;
				}
			}

		public bool IsReadOnly
			{
			get
				{
				return false;
				}
			}

		public bool Remove(T item)
			{
			return _container.Remove(item);
			}

		#endregion


		#region IEnumerable<T> Members

		public IEnumerator<T> GetEnumerator()
			{
			return _container.Keys.GetEnumerator();
			}

		#endregion


		#region IEnumerable Members

		System.Collections.IEnumerator System.Collections.IEnumerable.GetEnumerator()
			{
			throw new Exception("The method or operation is not implemented.");
			}

		#endregion
		}
	}
